package intermediate;

import com.sandwich.koan.Koan;

import java.util.Arrays;
import java.util.Comparator;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutComparison {

    @Koan
    public void compareObjects() {
        String a = "abc";
        String b = "bcd";
        // difference in unicode representation of the first non-matching character
        assertEquals(a.compareTo(b), -1);
        assertEquals(a.compareTo(a), 0);
        // difference in unicode representation of the first non-matching character
        assertEquals(b.compareTo(a), 1);
    }

    static class Car implements Comparable<Car> {
        int horsepower;

        // For an explanation for this implementation look at
        // http://download.oracle.com/javase/6/docs/api/java/lang/Comparable.html#compareTo(T)
        public int compareTo(Car o) {
            return horsepower - o.horsepower;
        }

    }

    @Koan
    public void makeObjectsComparable() {
        Car vwbeetle = new Car();
        vwbeetle.horsepower = 50;
        Car porsche = new Car();
        porsche.horsepower = 300;
        assertEquals(vwbeetle.compareTo(porsche), -250);
    }

    static class RaceHorse {
        int speed;
        int age;

        @Override
        public String toString() {
            return "Speed: " + speed + " Age: " + age;
        }
    }

    static class HorseSpeedComparator implements Comparator<RaceHorse> {
        public int compare(RaceHorse o1, RaceHorse o2) {
            return o1.speed - o2.speed;
        }
    }

    static class HorseAgeComparator implements Comparator<RaceHorse> {
        public int compare(RaceHorse o1, RaceHorse o2) {
            return o1.age - o2.age;
        }
    }

    @Koan
    public void makeObjectsComparableWithoutComparable() {
        RaceHorse lindy = new RaceHorse();
        lindy.age = 10;
        lindy.speed = 2;
        RaceHorse lightning = new RaceHorse();
        lightning.age = 2;
        lightning.speed = 10;
        RaceHorse slowy = new RaceHorse();
        slowy.age = 12;
        slowy.speed = 1;

        RaceHorse[] horses = {lindy, slowy, lightning};

        Arrays.sort(horses, new HorseAgeComparator());
        // note: using "Speed: 2 Age: 10" string doesn't work.  Because that string
        // literal will have a different reference than horses[0].  Need to use
        // lightning, which has the same reference as horses[0].
        assertEquals(horses[0], lightning);
        Arrays.sort(horses, new HorseSpeedComparator());
        // same as above, need to use the slowy reference, which will be equal to horses[0]
        assertEquals(horses[0], slowy);
    }
}
