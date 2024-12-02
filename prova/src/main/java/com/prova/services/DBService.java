package com.prova.services;

import com.prova.domains.Carro;
import com.prova.domains.Cliente;
import com.prova.domains.Funcionario;
import com.prova.domains.Venda;
import com.prova.repositories.ClienteRepository;
import com.prova.repositories.CarroRepository;
import com.prova.repositories.FuncionarioRepository;
import com.prova.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void initDB() {

        // Alterando os dados dos clientes
        Cliente cliente1 = new Cliente(encoder.encode("12345678901"), "Carlos Alberto Silva", 1L);
        Cliente cliente2 = new Cliente(encoder.encode("98765432100"), "Ana Beatriz Costa", 2L);

        // Alterando os dados dos funcionários
        Funcionario funcionario1 = new Funcionario(encoder.encode("33344455566"), "João Pedro Souza", 3L);
        Funcionario funcionario2 = new Funcionario(encoder.encode("66677788899"), "Maria Fernanda Lima", 4L);

        // Criando as vendas
        Venda venda1 = new Venda(null, 20000.00, cliente1);
        Venda venda2 = new Venda(null, 40000.00, cliente2);

        // Criando os carros
        Carro carro1 = new Carro();
        carro1.setNomeCarro("Fusca 1975");
        carro1.setMarca("Volkswagen");
        carro1.setValorCompra(5000.00);
        carro1.setValorVenda(8000.00);
        carro1.setKilometragemCarro("120000 km");
        carro1.setTipoProduto("Inativo");
        carro1.setVenda(venda1); // Associa o carro à venda

        /*{
            "username": "João Pedro Souza",
            "password": "33344455566"
        }*/

        //Authorization
        
        Carro carro2 = new Carro();
        carro2.setNomeCarro("Chevrolet Onix 2022");
        carro2.setMarca("Chevrolet");
        carro2.setValorCompra(35000.00);
        carro2.setValorVenda(45000.00);
        carro2.setKilometragemCarro("30000 km");
        carro2.setTipoProduto("Ativo");
        carro2.setVenda(venda2); // Associa o carro à venda

        // Associando as vendas aos clientes
        cliente1.getVendas().add(venda1);
        cliente2.getVendas().add(venda2);

        // Salvando os dados no banco de dados
        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);
        funcionarioRepository.save(funcionario1);
        funcionarioRepository.save(funcionario2);
        vendaRepository.save(venda1);
        vendaRepository.save(venda2);
        carroRepository.save(carro1);  // Salvando o carro1
        carroRepository.save(carro2);  // Salvando o carro2
    }
    /*{
            "nomeCarro": "teste",
            "marca": "teste",
            "valorCompra":"1",
            "valorVenda": "1",
            "kilometragemCarro": "teste",
            "tipoProduto": "1"
        }*/
}
