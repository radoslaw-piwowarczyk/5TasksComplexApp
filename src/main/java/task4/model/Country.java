package task4.model;

import java.util.Objects;

public class Country implements Comparable<Country> {
    private final String name;
    private final int gdp;

    public Country(String name, int gdp) {
        this.name = name;
        this.gdp = gdp;
    }

    public String getName() {
        return name;
    }

    public int getGdp() {
        return gdp;
    }

    @Override
    public int compareTo(Country country) {
        return this.name.compareTo(country.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return gdp == country.gdp && name.equals(country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gdp);
    }

    @Override
    public String toString() {
        return String.format("%-25s %s", name, gdp);
    }
}
