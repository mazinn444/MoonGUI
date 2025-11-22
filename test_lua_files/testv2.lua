print(">>> Iniciando Teste Pro...")

-- 1. Janela Principal
local win = MoonGUI:Window("MoonGUI Pro Features", 450, 500)
win:centerOnScreen()

-- 2. Criando o Menu Superior (Nível 5)
local menuBar = MoonGUI:MenuBar()

-- Tabela de itens para o menu 'Arquivo'
local menuArquivo = {
    { label = "Novo", action = function() print("Clicou Novo") end },
    { label = "Sair", action = function() win:close() end }
}
menuBar:addMenu("Arquivo", menuArquivo)

local menuAjuda = {
    { label = "Sobre", action = function() MoonGUI:Alert("MoonGUI v1.0\nCriado com Java + Lua") end }
}
menuBar:addMenu("Ajuda", menuAjuda)


-- 3. Layout Vertical Principal (Nível 1)
-- VBox organiza tudo um embaixo do outro automaticamente
local mainLayout = MoonGUI:VBox()
mainLayout:setSpacing(15)
mainLayout:setAlignment("TOP_CENTER")

-- Adiciona o Menu no topo
mainLayout:add(menuBar)


-- 4. Teste de Entrada e Teclado (Nível 3)
local lblInput = MoonGUI:Label("Digite algo e aperte ENTER:")
lblInput:style("-fx-font-weight: bold; -fx-padding: 10 0 0 0;") -- CSS (Nível 4)

local txtInput = MoonGUI:TextField("")
txtInput:setPrompt("Pressione ENTER aqui...")
txtInput:resize(300, 35) -- Largura fixa dentro do layout

-- Evento de Teclado
txtInput:OnKeyPress(function(tecla)
    if tecla == "ENTER" then
        local texto = txtInput:getText()
        MoonGUI:Alert("Você digitou e deu Enter:\n" .. texto)
        txtInput:clear()
    end
end)

mainLayout:add(lblInput)
mainLayout:add(txtInput)


-- 5. Teste de HTTP e JSON (Nível 2)
local separator = MoonGUI:Label("-----------------------------------------")
mainLayout:add(separator)

local btnDownload = MoonGUI:Button("Baixar Dados da Internet (JSON)")
btnDownload:resize(300, 45)
btnDownload:style("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-font-weight: bold;")

local lblStatus = MoonGUI:Label("Status: Aguardando...")
lblStatus:style("-fx-text-fill: #7f8c8d;")

local txtResultado = MoonGUI:TextArea("Os dados aparecerão aqui...")
txtResultado:resize(350, 150)
txtResultado:setWrapText(true)
txtResultado:setEditable(false)

btnDownload:OnClick(function()
    lblStatus:setText("Status: Baixando...")
    btnDownload:setDisabled(true)
    
    -- Faz requisição GET para uma API pública de teste
    MoonGUI:HTTP_Get("https://jsonplaceholder.typicode.com/users/1", function(status, body)
        
        if status == 200 then
            lblStatus:setText("Status: Sucesso! (200 OK)")
            
            -- Parse do JSON (String -> Tabela Lua)
            local dados = MoonGUI:JSON_Parse(body)
            
            -- Acessando dados da tabela
            local nome = dados:get("name")
            local email = dados:get("email")
            local cidade = dados:get("address"):get("city")
            
            local relatorio = "DADOS RECEBIDOS:\n" ..
                              "Nome: " .. tostring(nome) .. "\n" ..
                              "Email: " .. tostring(email) .. "\n" ..
                              "Cidade: " .. tostring(cidade)
            
            txtResultado:setText(relatorio)
        else
            lblStatus:setText("Status: Erro " .. status)
            txtResultado:setText("Erro ao baixar dados.")
        end
        
        btnDownload:setDisabled(false)
    end)
end)

mainLayout:add(btnDownload)
mainLayout:add(lblStatus)
mainLayout:add(txtResultado)

-- Adiciona o Layout Mestre na Janela
-- Note que não usamos win:add(btn), adicionamos tudo no layout
win:add(mainLayout)