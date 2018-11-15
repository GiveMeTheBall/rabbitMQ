package qihoo.open.v2.model.campaign;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger logger = Logger.getLogger(App.class); 
    public static void main( String[] args )
    {
//    	BasicConfigurator.configure(); 
    	 logger.info("publisher 开始 ");
    	 logger.debug("publisher 开始 ");
        consumer();
        System.out.println( "Hello World!正在监听接收" );
        publisher();
        System.out.println( "Hello World!消息已經发布" );
    }
    
    public static void  consumer() {
    	 Connection connection = null;
         Channel channel = null;
         logger.info("consumer 开始 ");
         try
         {
             ConnectionFactory factory = new ConnectionFactory();
             factory.setHost("localhost");
             factory.setPort(5672);
             factory.setUsername("jjj");
             factory.setPassword("password");
             factory.setVirtualHost("test_vhosts");
             connection = factory.newConnection();
             channel = connection.createChannel();
  
             Consumer consumer = new DefaultConsumer(channel) {
                 @Override
                 public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                         throws IOException {
                     String message = new String(body, "UTF-8");
                     System.out.println(" Consumer have received '" + message + "'");
                 }
             };
             channel.basicConsume("JQueue", true, consumer);
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
         logger.debug("consumer jieshu ");
    }
    
    
    public static void publisher() 
    {
        Connection connection = null;
        Channel channel = null;
        logger.info("publisher 开始 ");
        try
        {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(ConnectionFactory.DEFAULT_HOST);
            factory.setPort(ConnectionFactory.DEFAULT_AMQP_PORT);
            factory.setUsername("username");
            factory.setPassword("password");
            factory.setVirtualHost("test_vhosts");
            //创建与RabbitMQ服务器的TCP连接
            connection  = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare("JQueue", true, false, false, null);
            String message = "First Message";           
            channel.basicPublish("", "JQueue", null, message.getBytes());
            System.out.println("Send Message is:'" + message + "'");            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(channel != null)
            {
                try {
					channel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if(connection != null)
            {
                try {
					connection.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        logger.info("publisher jishu  ");
    }
}
