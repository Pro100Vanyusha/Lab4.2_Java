
package insurance;

public abstract class Зобовязання {
    private String назва;
    private double вартість;
    private double ризик;

    public Зобовязання(String назва, double вартість, double ризик) {
        this.назва = назва;
        this.вартість = вартість;
        this.ризик = ризик;
    }

    public abstract double розрахуватиВартість();

    @Override
    public String toString() {
        return "Назва: " + назва + ", Вартість: " + вартість + ", Ризик: " + ризик;
    }
}

package insurance;

public class Дериватив extends Зобовязання {
    private Зобовязання[] зобовязання;

    public Дериватив(String назва, Зобовязання[] зобовязання) {
        super(назва, 0, 0);
        this.зобовязання = зобовязання;
    }

    @Override
    public double розрахуватиВартість() {
        double сумарнаВартість = 0;
        for (Зобовязання обов'язок : зобовязання) {
            сумарнаВартість += обов'язок.розрахуватиВартість();
        }
        return сумарнаВартість;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append("\nЗобов'язання в деривативі:");
        for (Зобовязання обов'язок : зобовязання) {
            stringBuilder.append("\n").append(обов'язок.toString());
        }
        return stringBuilder.toString();
    }
}

package insurance;

public class Автострахування extends Зобовязання {
    private int рікВипуску;

    public Автострахування(String назва, double вартість, double ризик, int рікВипуску) {
        super(назва, вартість, ризик);
        this.рікВипуску = рікВипуску;
    }

    @Override
    public double розрахуватиВартість() {
        return вартість * (1 + ризик / 100);
    }
}

package insurance;

public class Життєве_страхування extends Зобовязання {
    private int термінДії;

    public Життєве_страхування(String назва, double вартість, double ризик, int термінДії) {
        super(назва, вартість, ризик);
        this.термінДії = термінДії;
    }

    @Override
    public double розрахуватиВартість() {
        return вартість * (1 + ризик / 100) * термінДії;
    }
}