package intermediate;

import com.sandwich.koan.Koan;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutEquality {
    // This suite of Koans expands on the concepts introduced in beginner.AboutEquality

    @Koan
    public void sameObject() {
        Object a = new Object();
        Object b = a;
        assertEquals(a == b, true);
    }

    @Koan
    public void equalObject() {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        assertEquals(a.equals(b), true);
        assertEquals(b.equals(a), true);
    }

    @Koan
    public void noObjectShouldBeEqualToNull() {
        assertEquals(new Object().equals(null), false);
    }

    static class Car {
        private String name = "";
        private int horsepower = 0;

        public Car(String s, int p) {
            name = s;
            horsepower = p;
        }

        /**
         * IDE-generated equals
         * @param o The object to compare
         * @return True if equal
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Car car = (Car) o;

            if (horsepower != car.horsepower) return false;
            return name != null ? name.equals(car.name) : car.name == null;

        }

        /**
         * IDE-generated hashCode
         * @return Hash code
         */
        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + horsepower;
            return result;
        }

        /*
        @Override
        public boolean equals(Object other) {
            // Change this implementation to match the equals contract
            // Car objects with same horsepower and name values should be considered equal
            // http://download.oracle.com/javase/6/docs/api/java/lang/Object.html#equals(java.lang.Object)
            // Another site explaining this: http://www.artima.com/lejava/articles/equality.html
            return other != null
                    && this.getClass().equals(other.getClass())
                    && this.name.equals(((Car) other).name)
                    && this.horsepower == ((Car) other).horsepower;
        }

        @Override
        public int hashCode() {
            // @see http://download.oracle.com/javase/6/docs/api/java/lang/Object.html#hashCode()
            return super.hashCode();
        }
        */
    }

    @Koan
    public void equalForOwnObjects() {
        Car car1 = new Car("Beetle", 50);
        Car car2 = new Car("Beetle", 50);
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(car2), true);
        assertEquals(car2.equals(car1), true);
    }

    @Koan
    public void unequalForOwnObjects() {
        Car car1 = new Car("Beetle", 50);
        Car car2 = new Car("Porsche", 300);
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(car2), false);
    }

    @Koan
    public void unequalForOwnObjectsWithDifferentType() {
        Car car1 = new Car("Beetle", 50);
        String s = "foo";
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(s), false);
    }

    @Koan
    public void equalNullForOwnObjects() {
        Car car1 = new Car("Beetle", 50);
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(null), false);
    }

    @Koan
    public void ownHashCode() {
        // As a general rule: When you override equals you should override
        // hash code
        // Read the hash code contract to figure out why
        // http://download.oracle.com/javase/6/docs/api/java/lang/Object.html#hashCode()

        // Implement Car.hashCode around line 51 so that the following assertions pass
        Car car1 = new Car("Beetle", 50);
        Car car2 = new Car("Beetle", 50);
        assertEquals(car1.equals(car2), true);
        assertEquals(car1.hashCode() == car2.hashCode(), true);
    }

    static class Chicken {
        String color = "green";

        @Override
        public int hashCode() {
            int result = color != null ? color.hashCode() : 0;
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Chicken))
                return false;
            return ((Chicken) other).color.equals(color);
        }
    }

    @Koan
    public void ownHashCodeImplementationPartTwo() {
        Chicken chicken1 = new Chicken();
        chicken1.color = "black";
        Chicken chicken2 = new Chicken();
        assertEquals(chicken1.equals(chicken2), false);
        assertEquals(chicken1.hashCode() == chicken2.hashCode(), false);
        // Does this still fit the hashCode contract? Why (not)?
        // No. Non-equivalent objects hash to the same value (4000, originally, above)
        // Fix the Chicken class to correct this. // Fixed
    }

}
