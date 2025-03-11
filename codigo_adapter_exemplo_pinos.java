// Classe que representa um buraco redondo
class RoundHole {
    private int radius;

    public RoundHole(int radius) {
        this.radius = radius;
    }

    // Método para verificar se um pino redondo se encaixa no buraco
    public boolean fits(RoundPeg peg) {
        return this.radius >= peg.getRadius();
    }
}

// Classe que representa um pino redondo
class RoundPeg {
    private int radius;

    public RoundPeg(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }
}

// Classe que representa um pino quadrado
class SquarePeg {
    private int width;

    public SquarePeg(int width) {
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }
}

// Adaptador que permite usar pinos quadrados como se fossem redondos
class SquarePegAdapter extends RoundPeg {
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        // Chama o construtor da classe pai (RoundPeg), mas o valor será ajustado em getRadius()
        super(peg.getWidth());
        this.peg = peg;
    }

    @Override
    public int getRadius() {
        // Converte a largura do pino quadrado em um raio equivalente
        return (int) (Math.sqrt(2) * this.peg.getWidth() / 2);
    }
}

// Classe principal para testar o adaptador
public class Main {
    public static void main(String[] args) {
        // Criando um buraco redondo com raio 5
        RoundHole hole = new RoundHole(5);
        System.out.println("Criado um buraco redondo com raio 5.");

        // Criando um pino redondo de raio 5
        RoundPeg rpeg = new RoundPeg(5);

        // Testando se o pino redondo se encaixa no buraco redondo
        if (hole.fits(rpeg)) {
            System.out.println("Round peg r5 fits round hole r5.");
        } else {
            System.out.println("Round peg r5 does not fit round hole r5.");
        }

        // Criando dois pinos quadrados, um pequeno (largura 2) e um grande (largura 20)
        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);
        System.out.println("Criados pinos quadrados com larguras 2 e 20.");

        // Criando adaptadores para transformar pinos quadrados em pinos redondos
        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);

        // Testando se o pino quadrado pequeno (convertido para redondo) se encaixa
        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("Square peg w2 fits round hole r5.");
        } else {
            System.out.println("Square peg w2 does not fit round hole r5.");
        }

        // Testando se o pino quadrado grande (convertido para redondo) se encaixa
        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("Square peg w20 does not fit into round hole r5.");
        }
    }
}
