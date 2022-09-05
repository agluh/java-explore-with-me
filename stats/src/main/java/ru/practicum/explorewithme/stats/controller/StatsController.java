package ru.practicum.explorewithme.stats.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.stats.dto.EndpointHitDto;
import ru.practicum.explorewithme.stats.model.ViewsStats;
import ru.practicum.explorewithme.stats.service.StatsService;

@RestController
@AllArgsConstructor
public class StatsController {

    private final StatsService service;

    @PostMapping("/hit")
    public void hit(@RequestBody @Valid EndpointHitDto hit) {
        service.recordHit(hit.getApp(), hit.getUri(), hit.getIp(), hit.getTimestamp());
    }

    @GetMapping("/stats")
    public List<ViewsStats> getStats(
        @RequestParam(name = "start") long start,
        @RequestParam(name = "end") long end,
        @RequestParam(name = "uris", required = false) List<String> uris,
        @RequestParam(name = "unique", defaultValue = "false") boolean unique
    ) {
        return service.findStats(start, end, uris, unique);
    }
}
