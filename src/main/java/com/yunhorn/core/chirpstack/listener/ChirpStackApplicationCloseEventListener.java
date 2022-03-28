package com.yunhorn.core.chirpstack.listener;

import com.yunhorn.core.chirpstack.helper.ThreadPoolHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @author ljm
 * @date 2022/3/28 11:58
 */
@Slf4j
@Component
public class ChirpStackApplicationCloseEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        ThreadPoolHelper.closeThreadPool();
        log.info("chirpStack-syncer close");
    }
}
