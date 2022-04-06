import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;


import java.util.*;

public class Main {

        public static void main(String[] args) throws JsonProcessingException {
            System.out.println("main");
            ObjectMapper objectMapper = new ObjectMapper();

            User user = new User();
            user.setName("hong");
            user.setAge(25);

            Car car1 = new Car();
            car1.setName("k5");
            car1.setCarNumber("11가 1111");
            car1.setType("sedan");

            Car car2 = new Car();
            car2.setName("q5");
            car2.setCarNumber("22가 2222");
            car2.setType("suv");

            List<Car> carList = Arrays.asList(car1,car2);
            user.setCars(carList);

            System.out.println(user);

            String json = objectMapper.writeValueAsString(user);
            System.out.println(json);


            JsonNode jsonNode = objectMapper.readTree(json);
            String name = jsonNode.get("name").asText();
            int age = jsonNode.get("age").asInt();
            System.out.println("name : "+name);
            System.out.println("age : "+age);
            //json타입이 객체이면 그것도 하나에 node이다

            JsonNode cars = jsonNode.get("cars");
            ArrayNode arrayNode = (ArrayNode)cars;
                                                        //converValue 우리가 원하는형태로 매핑
            List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {
            });

            System.out.println(_cars);
            //json을 바꾸려면 ObjectNode를 써야한다.
            ObjectNode objectNode = (ObjectNode)jsonNode;
            objectNode.put("name","kim");
            objectNode.put("age",20);

            System.out.println(objectNode.toPrettyString());
        }
}
