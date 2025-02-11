package IniFlex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    // Construtor
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    // Getters e Setters
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        // Formata a data no padrão dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = getDataNascimento().format(formatter);

        // Formata o salário com separador de milhar e decimal
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        String salarioFormatado = numberFormat.format(salario);

        return "Funcionario{" +
                "nome='" + getNome() + '\'' +
                ", dataNascimento=" + dataFormatada +
                ", salario=" + salarioFormatado +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}