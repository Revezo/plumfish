package com.traanite.plumfish.error;


import com.traanite.plumfish.raffle.model.RafflePackageDrawFailed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.traanite.plumfish.commons.events.publisher.RabbitGeneralQueuesConfig.ERROR_QUEUE_1;

@Service
@Slf4j
@RequiredArgsConstructor
public class ErrorConsumer {

    @RabbitListener(queues = ERROR_QUEUE_1)
    public void receiveMessage(final RafflePackageDrawFailed rafflePackageDrawFailed) {
        log.info("RECEIVED: {}", rafflePackageDrawFailed);
    }

}