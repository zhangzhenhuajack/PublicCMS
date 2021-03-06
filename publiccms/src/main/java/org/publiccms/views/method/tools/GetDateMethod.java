package org.publiccms.views.method.tools;

import static org.apache.commons.lang3.time.DateUtils.addDays;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.publiccms.common.base.BaseMethod;

import freemarker.template.TemplateModelException;

/**
 *
 * GetDateMethod
 * 
 */
@Component
public class GetDateMethod extends BaseMethod {

    @SuppressWarnings("unchecked")
    @Override
    public Object exec(@SuppressWarnings("rawtypes") List arguments) throws TemplateModelException {
        Date date;
        if (arguments.size() >= 2) {
            try {
                date = getDate(1, arguments);
            } catch (ParseException e) {
                date = getDate();
            }
        } else {
            date = getDate();
        }
        if (arguments.size() >= 1) {
            String type = getString(0, arguments);
            switch (type) {
            case "thisSunday":
                date = getThisSunday(date);
                break;
            case "thisMonday":
                date = getThisMonday(date);
                break;
            case "lastMonday":
                date = getLastMonday(date);
                break;
            case "lastSunday":
                date = getLastSunday(date);
                break;
            }
        }
        return date;
    }
    
    @Override
    public boolean needAppToken() {
        return false;
    }

    @Override
    public int minParamtersNumber() {
        return 0;
    }

    Date getThisMonday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDays(date, -1));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    Date getThisSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDays(date, 6));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    Date getLastMonday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDays(date, -8));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    Date getLastSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDays(date, -1));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

}
