package no.difi.kontaktregister.statistics.fetch.service;

import no.difi.statistics.ingest.client.IngestClient;
import no.difi.statistics.ingest.client.model.TimeSeriesDefinition;
import no.difi.statistics.ingest.client.model.TimeSeriesPoint;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static no.difi.statistics.ingest.client.model.MeasurementDistance.hours;

public class LastDatapoint {
    private final IngestClient ingestClient;
    private final static ZonedDateTime baseTime = ZonedDateTime.of(2015, 5, 1, 0, 0, 0, 0, ZoneId.of("UTC"));

    public LastDatapoint(IngestClient ingestClient) {
        this.ingestClient = ingestClient;
    }

    public ZonedDateTime get(String seriesName) {
        return ingestClient.last(TimeSeriesDefinition.builder().name(seriesName).distance(hours))
                .map(TimeSeriesPoint::getTimestamp).orElse(baseTime);
    }
}
