package Shared;

import javax.jms.*;

public class Connector {

    private String subject;
    private IListener listener;

    private Connection_Factory connectionFactory = new Connection_Factory(); // connectionFactory
    Session session; // session for creating messages, producers and
    Destination destination;

    MessageConsumer consumer; // for sending messages;

    public Connector(String queueName, IListener listener){
        subject = queueName;
        this.listener = listener;

        try {
            connection();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void connection() throws JMSException {
        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");

        // Moved this factory.
//      ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.getConnectionFactory().createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        destination = session.createTopic(subject);

        consumer = session.createConsumer(destination);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try{
                    receiveMessage(message);
                } catch (JMSException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendMessage(Car message, String channel, String typeMessage){
        System.out.println("Sending message: " + session);

        Destination producerDestination = null;
        MessageProducer messageProducer = null;
        ObjectMessage objectMessage = null;

        try {
            producerDestination = session.createTopic(channel);
            messageProducer = session.createProducer(producerDestination);
            objectMessage = session.createObjectMessage(message);
            messageProducer.send(objectMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }

        System.out.println("A car " + typeMessage + " has been sent: " + message.toString());
    }

    public void receiveMessage(Message message) throws JMSException {

        if (message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Car carMessage = (Car) objectMessage.getObject();

            if(carMessage.type != null){
                switch (carMessage.type){
                    case REPLY:
                        System.out.println("REPLY CASE");
                        listener.onMessage(carMessage);
                        break;
                    case REQUEST:
                        System.out.println("REQUEST CASE");
                        listener.onMessage(carMessage);
                        break;
                }
            }
        }
    }
}
