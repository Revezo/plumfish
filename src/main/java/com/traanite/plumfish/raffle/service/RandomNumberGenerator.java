package com.traanite.plumfish.raffle.service;

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
public class RandomNumberGenerator {
    private static final String RANDOM_API_URI = "https://api.random.org/json-rpc/2/invoke";

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final Random random;

    public int randomInt(Range range) throws RandomNumberGeneratorException {
        RandomRequest randomRequest = new RandomRequest(random.nextLong(), new RandomRequestParams(apiKey, 1, range.getMin(), range.getMax()));
        RandomResponse randomResponse = restTemplate.postForObject(RANDOM_API_URI, randomRequest, RandomResponse.class);
        if (!isResponseValid(randomResponse)) {
            throw new RandomNumberGeneratorException("Couldn't get response for request: " + randomRequest);
        }
        return randomResponse.getResult().getRandom().getData().get(0);
    }

    private boolean isResponseValid(RandomResponse randomResponse) {
        return (randomResponse != null && randomResponse.getResult() != null
                && randomResponse.getResult().getRandom().getData() != null
                && randomResponse.getResult().getRandom().getData().size() > 0);
    }


    @Value
    static class RandomRequest {
        String jsonrpc = "2.0";
        String method = "generateIntegers";
        long id;
        RandomRequestParams params;
    }

    @Value
    static class RandomRequestParams {
        String apiKey;
        long n;
        long min;
        long max;
    }

    @Value
    static class RandomResponse {
        long id;
        String jsonrpc;
        RandomResponseResult result;
    }

    @Value
    static class RandomResponseResult {
        RandomResponseData random;
        int bitsUsed;
        int bitsLeft;
        int requestsLeft;
    }

    @Value
    static class RandomResponseData {
        List<Integer> data;
        SimpleDateFormat completionTime; //TODO use something from java.time
    }
}

