package ru.practicum.explorewithme.stats.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ViewsStats {

    private String app;

    private String uri;

    private long hits;
}
