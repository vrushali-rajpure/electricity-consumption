package com.models.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.EqualsAndHashCode;

import static java.util.stream.Collectors.toList;

@EqualsAndHashCode
public class Floor {

    private int floorNumber;
    private List<Corridor> corridors;

    private Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.corridors = new ArrayList<>(0);
    }

    public Floor(int floorNumber, int mainCorridorCount, int subCorridorCount) {
        this(floorNumber);
        createCorridor(mainCorridorCount, Corridor::main);
        createCorridor(subCorridorCount, Corridor::sub);
    }

    //TODO : used only for testing should be removed :(
    public List<Corridor> getCorridors() {
        return Collections.unmodifiableList(corridors);
    }

    @Override
    public String toString() {
        final Optional<String> corridors = this.corridors
            .stream()
            .map(Corridor::toString)
            .reduce((a, b) -> a + "\n" + b);

        return "Floor " + floorNumber + "\n" +
            corridors.orElse("");
    }

    public int mainCorridorCount() {
        return (int) filterCorridorWith(Corridor::isMain).count();
    }

    public int subCorridorCount() {
        return subCorridors().size();
    }

    public int getConsumption() {
        return corridors
            .stream()
            .map(Corridor::getConsumption)
            .reduce(Integer::sum).orElse(0);
    }

    public boolean isFloorWithNumber(int inputFloorNumber) {
        return this.floorNumber == inputFloorNumber;
    }

    public List<Corridor> subCorridors() {
        Predicate<Corridor> isMain = Corridor::isMain;
        return filterCorridorWith(isMain.negate()).collect(toList());
    }

    private Stream<Corridor> filterCorridorWith(Predicate<Corridor> filter) {
        return corridors
            .stream()
            .filter(filter);
    }

    private void createCorridor(int totalCount, Function<Integer, Corridor> buildFn) {
        int index = 0;
        while (index < totalCount) {
            corridors.add(buildFn.apply(index + 1));
            index++;
        }
    }
}
