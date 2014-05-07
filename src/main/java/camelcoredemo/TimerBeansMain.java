package camelcoredemo;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Test Apache Camel
 * This basic program can be executed from the command line with maven: mvn compile exec:java
 * and will setup a route with a timer (every 5 sec) sending to a bean and then a file
 * receiver.
 * 
 * Note: the file processor path is relevant to the current diretory.
 */
public class TimerBeansMain extends Main {
    static Logger LOG = LoggerFactory.getLogger(TimerBeansMain.class);
    
    public static void main(String... args) throws Exception {
        TimerBeansMain main = new TimerBeansMain();
        main.enableHangupSupport();
        main.bind("processByBean1", new Bean1());
        main.bind("processAgainByBean2", new Bean2());
        main.addRouteBuilder(createRouteBuilder());
        main.run(args);
    }
    
    static RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
                public void configure() {
                    from("timer://timer1?period=5000")
                    .to("bean:processByBean1")
                    //.to("bean:processAgainByBean2")
                    .to("file://test_file_output?fileName=mydata-${date:now:yyyyMMdd-HHmmss}.txt");
                }
            };
    }
    
    // Processor beans
    static class Bean1 implements Processor {
        public void process(Exchange msg) {
            Message m = msg.getOut();
            m.setBody("this is some message body data");

            LOG.info("First process {}", msg);
        }
    }
    
    static class Bean2 implements Processor {
        public void process(Exchange msg) {
            LOG.info("Second process {}", msg);
        }
    }
}
