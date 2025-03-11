// Classe representando o sistema antigo, que recebe um clienteId numérico e um horário
class SistemaAntigo {
    public void agendarHorario(int clienteId, String horario) {
        System.out.println("✅ Agendamento realizado para o cliente " + clienteId + " no horário " + horario);
        System.out.flush(); // Garante que a saída seja exibida no console
    }
}

// Interface representando o novo sistema, que aceita um identificador de cliente como String
interface SistemaNovo {
    void agendarServico(String cliente, String dataHora);
}

// Classe Adaptadora que converte os dados para permitir a compatibilidade entre os sistemas
class SistemaAdaptador implements SistemaNovo {
    private SistemaAntigo sistemaAntigo;

    // Construtor que recebe a instância do sistema antigo
    public SistemaAdaptador(SistemaAntigo sistemaAntigo) {
        this.sistemaAntigo = sistemaAntigo;
    }

    // Método do novo sistema que precisa ser adaptado para chamar o sistema antigo
    @Override
    public void agendarServico(String cliente, String dataHora) {
        try {
            // Conversão do identificador de cliente de String para int
            int clienteId = Integer.parseInt(cliente);
            // Chamando o método do sistema antigo com os dados convertidos
            sistemaAntigo.agendarHorario(clienteId, dataHora);
        } catch (NumberFormatException e) {
            // Tratamento de erro caso o identificador não seja numérico
            System.out.println("❌ Erro: O identificador do cliente deve ser numérico.");
            System.out.flush();
        }
    }
}

// Classe principal para testar o adaptador
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Criando uma instância do sistema antigo
        SistemaAntigo sistemaAntigo = new SistemaAntigo();

        // Criando um adaptador que permite que o novo sistema se comunique com o antigo
        SistemaNovo sistemaNovo = new SistemaAdaptador(sistemaAntigo);

        // Testando com um cliente válido (numérico)
        System.out.println("🔹 Teste 1: Cliente válido");
        sistemaNovo.agendarServico("123", "10:30");

        // Testando com um cliente inválido (não numérico)
        System.out.println("\n🔹 Teste 2: Cliente inválido");
        sistemaNovo.agendarServico("ABC", "11:00");

        // Espera 2 segundos para garantir que o JVDroid exiba a saída antes de fechar
        Thread.sleep(2000);
    }
}
