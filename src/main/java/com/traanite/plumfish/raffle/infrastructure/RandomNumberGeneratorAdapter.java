package com.traanite.plumfish.raffle.infrastructure;

import com.traanite.plumfish.commons.events.exception.NullResponseException;
import com.traanite.plumfish.raffle.model.RandomNumberGenerator;
import com.traanite.plumfish.raffle.model.RandomNumberGeneratorError;
import com.traanite.plumfish.raffle.model.Range;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Slf4j
public class RandomNumberGeneratorAdapter implements RandomNumberGenerator {
    private static final String RANDOM_API_URI = "https://api.random.org/json-rpc/2/invoke";

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final Random random;

    public int randomInt(Range range) throws NullResponseException, RandomNumberGeneratorError {
        RandomRequest randomRequest = new RandomRequest(random.nextLong(), new RandomRequestParams(apiKey, 1, range.getMin(), range.getMax()));
        RandomResponse randomResponse = restTemplate.postForObject(RANDOM_API_URI, randomRequest, RandomResponse.class);

        validateResponseOrThrowError(randomRequest, randomResponse);
        // TODO don't alow null pointer here
        return randomResponse.getResult().getRandom().getData().get(0);
    }

    private void validateResponseOrThrowError(RandomRequest randomRequest, RandomResponse randomResponse) throws RandomNumberGeneratorError, NullResponseException {
        if (!isValidResponse(randomResponse)) {
            throw new NullResponseException(RANDOM_API_URI, randomRequest.toString());
        }
        if (randomResponse.getError() != null) {
            throw new RandomNumberGeneratorError(randomResponse.getError().getCode(), randomResponse.getError().getMessage(), randomResponse.getError().getData());
        }
    }

    private boolean isValidResponse(RandomResponse randomResponse) {
        return randomResponse != null &&
                ((randomResponse.getResult() != null && randomResponse.getResult().getRandom().getData() != null
                        && !randomResponse.getResult().getRandom().getData().isEmpty())
                || randomResponse.getError() != null);
    }


    @Value
    private static class RandomRequest {
        String jsonrpc = "2.0";
        String method = "generateIntegers";
        long id;
        RandomRequestParams params;
    }

    @Value
    private static class RandomRequestParams {
        String apiKey;
        long n;
        long min;
        long max;
    }

    @Value
    private static class RandomResponse {

        long id;
        String jsonrpc;
        RandomResponseResult result;
        RandomResponseError error;
    }

    @Value
    private static class RandomResponseError {
        int code;
        String message;
        List<Integer> data;
    }

    @Value
    private static class RandomResponseResult {
        RandomResponseData random;
        int bitsUsed;
        int bitsLeft;
        int requestsLeft;
    }

    @Value
    private static class RandomResponseData {
        List<Integer> data;
        SimpleDateFormat completionTime; //TODO use something from java.time
    }
}

