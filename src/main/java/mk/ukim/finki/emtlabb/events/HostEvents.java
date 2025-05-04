package mk.ukim.finki.emtlabb.events;

import lombok.Getter;
import mk.ukim.finki.emtlabb.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostEvents extends ApplicationEvent {
    private final LocalDateTime when;
    private final String action;

    public HostEvents(Host source, String action){
        super(source);
        this.action = action;
        this.when = LocalDateTime.now();
    }


//    public HostEvents(Host source, LocalDateTime when, String action) {
//        super(source);
//        this.when = when;
//        this.action = action;
//    }

    public LocalDateTime getWhen() {
        return when;
    }

    public String getAction() {
        return action;
    }
}
