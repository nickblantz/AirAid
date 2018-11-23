/*
 * Created by Nicholas Blantz on 2018.11.22  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.alerting;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Nick Blantz
 */
@ApplicationScoped
public class AlertTimer extends Timer{
    
    public void scheduleAlert(AlertTask task, Date alertTime, Long alertAdvance) {
        super.schedule((TimerTask) task, new Date(alertTime.getTime() - alertAdvance));
    }
}
