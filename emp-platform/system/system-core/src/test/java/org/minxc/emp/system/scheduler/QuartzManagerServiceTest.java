package org.minxc.emp.system.scheduler;

import org.minxc.emp.system.BasicTest;
import org.junit.Test;

/**
 * @author didi
 */
public class QuartzManagerServiceTest extends BasicTest {

    private QuartzManagerService getQuartzManager() {

        return getApplicationContext().getBean(QuartzManagerService.class);
    }

}
