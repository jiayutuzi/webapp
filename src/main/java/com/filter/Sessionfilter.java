package com.filter;
import com.entity.Loginlog;
import com.dao.LogDao;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Sessionfilter implements HttpSessionListener{
    public void sessionCreated(HttpSessionEvent event) {
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        String username = (String) session.getAttribute("user");
        if(username!=null)
        {
            String identity = (String) session.getAttribute("identity");
            String logintime = (String) session.getAttribute("logintime");
            String ip = (String)session.getAttribute("ip");
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String logouttime=dateFormat.format(now);
            Loginlog record = new Loginlog(username, identity, logintime, logouttime, ip);
            LogDao logDao = new LogDao();
            logDao.addloginlog(record);
        }
    } 
}
