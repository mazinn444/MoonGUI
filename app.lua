local win = MoonGUI:Window("Registration Form", 400, 500)
win:centerOnScreen()
win:setResizable(false)

local title = MoonGUI:Label("Create Account")
title:move(0, 20)
title:resize(400, 30)
title:style("-fx-font-size: 22px; -fx-font-weight: bold; -fx-alignment: center; -fx-text-fill: #333;")
win:add(title)

local lblName = MoonGUI:Label("Full Name")
lblName:move(50, 70)
win:add(lblName)

local txtName = MoonGUI:TextField("")
txtName:move(50, 95)
txtName:resize(300, 35)
txtName:setPrompt("Enter your name")
win:add(txtName)

local lblEmail = MoonGUI:Label("Email")
lblEmail:move(50, 145)
win:add(lblEmail)

local txtEmail = MoonGUI:TextField("")
txtEmail:move(50, 170)
txtEmail:resize(300, 35)
txtEmail:setPrompt("user@example.com")
win:add(txtEmail)

local lblRole = MoonGUI:Label("Department")
lblRole:move(50, 220)
win:add(lblRole)

local cmbRole = MoonGUI:ComboBox()
cmbRole:move(50, 245)
cmbRole:resize(300, 35)
cmbRole:addItem("Development")
cmbRole:addItem("Design")
cmbRole:addItem("Marketing")
cmbRole:select(0)
win:add(cmbRole)

local lblPass = MoonGUI:Label("Password")
lblPass:move(50, 295)
win:add(lblPass)

local txtPass = MoonGUI:PasswordField("")
txtPass:move(50, 320)
txtPass:resize(300, 35)
win:add(txtPass)

local chkAgree = MoonGUI:CheckBox("I agree to the Terms and Conditions")
chkAgree:move(50, 370)
win:add(chkAgree)

local btnSubmit = MoonGUI:Button("REGISTER")
btnSubmit:move(50, 410)
btnSubmit:resize(300, 45)
btnSubmit:style("-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-cursor: hand;")

btnSubmit:OnClick(function()
    local name = txtName:getText()
    local email = txtEmail:getText()
    local pass = txtPass:getText()
    local agreed = chkAgree:isChecked()

    if name == "" or email == "" or pass == "" then
        MoonGUI:Alert("Error: Please fill in all fields.")
        return
    end

    if not agreed then
        MoonGUI:Alert("Error: You must accept the terms.")
        return
    end

    MoonGUI:Alert("Success!\nUser: " .. name .. "\nDepartment: " .. cmbRole:getSelected())
    
    txtName:clear()
    txtEmail:clear()
    txtPass:clear()
    chkAgree:setChecked(false)
end)

win:add(btnSubmit)