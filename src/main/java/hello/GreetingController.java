package hello;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class GreetingController {
	private static final String TIME_STAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	System.out.println("inside greeting()");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/c3info-service/getCardStatus/{cardId}")
    public List<Card> getCardStatus(@PathVariable String cardId) {
    	System.out.println("inside getCardStatus(). cardId is " + cardId);
    	
        return createCardStatus(cardId);
    }
    
    List<Card> createCardStatus(String cardId) {
    	List<Card> statusList = new ArrayList<Card>(6);
    	statusList.add(new Card(cardId, "C3", "Card request was generated at C3", "arrivalTimestamp", "Card was sent to ESB", "leaveTimestamp"));
    	statusList.add(new Card(cardId, "ESB", "Card arrived at ESB", "arrivalTimestamp", "Card was sent to EPMS", "leaveTimestamp"));
    	statusList.add(new Card(cardId, "EPMS", "Card arrived at EPMS", "arrivalTimestamp", "Card was sent to NPS", "leaveTimestamp"));
    	statusList.add(new Card(cardId, "NPS", "Card arrived at NPS", "arrivalTimestamp", "Card was sent to CPSTR", "leaveTimestamp"));
    	statusList.add(new Card(cardId, "CPSTR", "Card arrived at CPSTR", "arrivalTimestamp", "Card was sent to SMI", "leaveTimestamp"));
    	statusList.add(new Card(cardId, "SMI", "Card arrived at SMI", "arrivalTimestamp", "Card was mailed out", "leaveTimestamp"));
    	
    	Random ran = new Random();
    	int max = 1 + ran.nextInt(6);
    	List<Card> returnList = statusList.subList(0, max);
    	
    	//latest one on the top
    	Collections.reverse(returnList);
    	
    	//randomly set datetime
    	LocalDateTime lastTime = LocalDateTime.now().minusDays(max);
    	
    	for (int i=0; i<returnList.size(); i++) {
    		returnList.get(i).setLeaveTimestamp(lastTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")));
    		lastTime = lastTime.minusMinutes(3 * max);
    		returnList.get(i).setArrivalTimestamp(lastTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")));
    		lastTime = lastTime.minusHours(max);
    	}
    	
    	if (returnList.size() < 6) {
    		returnList.get(0).setError("Error occured when the card was processed");
    		returnList.get(0).setLeaveStatus("Error occured when the card was processed");
    	}
    	
    	return returnList;
    }
    
    Card createCardObject(String cardId) {
    	
    	return new Card(cardId, "NPS", "Card sent to CPSTR", "at 10:12:05 06/01/2018", "", "");
    }
}
