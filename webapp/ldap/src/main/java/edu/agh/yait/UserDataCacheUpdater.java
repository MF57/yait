package edu.agh.yait;

import edu.agh.yait.userData.UserDataCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by mf57 on 22.05.2017.
 */
@Component
public class UserDataCacheUpdater {

    private final UserDataCache cache;
    private static final Logger logger = LoggerFactory.getLogger(UserDataCacheUpdater.class);


    @Autowired
    public UserDataCacheUpdater(UserDataCache cache) {
        this.cache = cache;
    }


    @Scheduled(fixedRate = 60000)
    public void updateCache() {
        cache.refresh();
        logger.info("Cache successfully updated");
    }
}
