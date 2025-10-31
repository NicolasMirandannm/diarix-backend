insert into enterprise (id, manager_id, name, cnpj, owner_name, owner_email, owner_phone, address, status, base_daily_rate, created_by, modified_by, version)
values
    ('1a9b56a1-8f73-4b21-8c9f-001', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Construtora Alpha', '45.123.987/0001-10', 'Carlos Silva', 'carlos.alpha@empresa.com', '(11) 98888-1111', 'Rua das Palmeiras, 120 - SP', 'ATIVO', 250.00, 'system', 'system', 1),
    ('1a9b56a1-8f73-4b21-8c9f-002', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Serviços Beta LTDA', '18.654.321/0001-55', 'Fernanda Souza', 'fernanda.beta@empresa.com', '(11) 97777-2222', 'Av. Paulista, 1000 - SP', 'ATIVO', 300.00, 'system', 'system', 1),
    ('1a9b56a1-8f73-4b21-8c9f-003', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Manutenção Gama', '32.987.654/0001-22', 'João Pereira', 'joao.gama@empresa.com', '(11) 96666-3333', 'Rua dos Lírios, 50 - SP', 'ATIVO', 220.00, 'system', 'system', 1),
    ('1a9b56a1-8f73-4b21-8c9f-004', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Reformas Delta', '12.345.678/0001-99', 'Mariana Costa', 'mariana.delta@empresa.com', '(11) 95555-4444', 'Av. Liberdade, 300 - SP', 'ATIVO', 270.00, 'system', 'system', 1),
    ('1a9b56a1-8f73-4b21-8c9f-005', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Limpeza Epsilon', '54.321.678/0001-77', 'Roberto Lima', 'roberto.epsilon@empresa.com', '(11) 94444-5555', 'Rua Aurora, 12 - SP', 'ATIVO', 200.00, 'system', 'system', 1);


insert into day_laborer (id, manager_id, name, cpf, phone_number, status, pix_key, created_by, modified_by, version)
values
    ('2b8f77b1-7e83-4d11-9a10-101', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Ana Oliveira', '123.456.789-00', '(11) 99999-1111', 'ATIVO', 'ana.oliveira@pix.com', 'system', 'system', 1),
    ('2b8f77b1-7e83-4d11-9a10-102', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Bruno Costa', '234.567.890-11', '(11) 99999-2222', 'ATIVO', 'bruno.costa@pix.com', 'system', 'system', 1),
    ('2b8f77b1-7e83-4d11-9a10-103', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Carla Souza', '345.678.901-22', '(11) 99999-3333', 'ATIVO', 'carla.souza@pix.com', 'system', 'system', 1),
    ('2b8f77b1-7e83-4d11-9a10-104', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Diego Santos', '456.789.012-33', '(11) 99999-4444', 'ATIVO', 'diego.santos@pix.com', 'system', 'system', 1),
    ('2b8f77b1-7e83-4d11-9a10-105', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Eduarda Lima', '567.890.123-44', '(11) 99999-5555', 'ATIVO', 'eduarda.lima@pix.com', 'system', 'system', 1),
    ('2b8f77b1-7e83-4d11-9a10-106', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Felipe Martins', '678.901.234-55', '(11) 99999-6666', 'ATIVO', 'felipe.martins@pix.com', 'system', 'system', 1),
    ('2b8f77b1-7e83-4d11-9a10-107', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Gabriela Nunes', '789.012.345-66', '(11) 99999-7777', 'ATIVO', 'gabriela.nunes@pix.com', 'system', 'system', 1),
    ('2b8f77b1-7e83-4d11-9a10-108', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Henrique Rocha', '890.123.456-77', '(11) 99999-8888', 'ATIVO', 'henrique.rocha@pix.com', 'system', 'system', 1),
    ('2b8f77b1-7e83-4d11-9a10-109', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'Isabela Mendes', '901.234.567-88', '(11) 99999-9999', 'ATIVO', 'isabela.mendes@pix.com', 'system', 'system', 1),
    ('2b8f77b1-7e83-4d11-9a10-110', 'ebae93ea-59e3-4810-8f37-693d59f6305b', 'João Almeida', '012.345.678-99', '(11) 98888-0000', 'ATIVO', 'joao.almeida@pix.com', 'system', 'system', 1);