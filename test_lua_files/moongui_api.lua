---@meta

-- ==========================================
-- CORE DEFINITIONS (UIComponent)
-- ==========================================

--- The base class for all visual components (except the Window itself).
---@class UIComponent
local UIComponent = {}

--- Moves the component to the specified coordinates relative to the window.
---@param x number The horizontal position.
---@param y number The vertical position.
function UIComponent:move(x, y) end

--- Resizes the component to the specified dimensions.
---@param width number The new width.
---@param height number The new height.
function UIComponent:resize(width, height) end

--- Applies JavaFX CSS styles to the component.
--- Example: `comp:style("-fx-background-color: red; -fx-font-size: 14px;")`
---@param css string The CSS style string.
function UIComponent:style(css) end

--- Controls the visibility of the component.
---@param visible boolean If true, the component is shown; otherwise, it is hidden.
function UIComponent:setVisible(visible) end

--- Disables or enables the component. Disabled components are greyed out and non-interactive.
---@param disabled boolean True to disable, false to enable.
function UIComponent:setDisabled(disabled) end

--- Sets a specific CSS ID for the component (useful for external stylesheets).
---@param id string The unique ID.
function UIComponent:setID(id) end

---@return number The current X position.
function UIComponent:getX() end

---@return number The current Y position.
function UIComponent:getY() end

---@return number The current width.
function UIComponent:getWidth() end

---@return number The current height.
function UIComponent:getHeight() end


-- ==========================================
-- SPECIFIC COMPONENTS
-- ==========================================

---@class ButtonWrapper : UIComponent
local ButtonWrapper = {}

--- Updates the text displayed on the button.
---@param text string
function ButtonWrapper:setText(text) end

---@return string The current text of the button.
function ButtonWrapper:getText() end

--- Registers a callback function to be executed when the button is clicked.
---@param callback fun() The Lua function to run.
function ButtonWrapper:OnClick(callback) end


---@class LabelWrapper : UIComponent
local LabelWrapper = {}
--- Updates the text of the label.
---@param text string
function LabelWrapper:setText(text) end
---@return string
function LabelWrapper:getText() end


---@class TextFieldWrapper : UIComponent
local TextFieldWrapper = {}
--- Sets the text content of the input field.
---@param text string
function TextFieldWrapper:setText(text) end
---@return string The current text in the field.
function TextFieldWrapper:getText() end
--- Sets the "ghost text" (placeholder) shown when the field is empty.
---@param text string
function TextFieldWrapper:setPrompt(text) end
--- Controls whether the user can type into this field.
---@param editable boolean
function TextFieldWrapper:setEditable(editable) end
--- Clears all text from the field.
function TextFieldWrapper:clear() end


---@class PasswordFieldWrapper : UIComponent
local PasswordFieldWrapper = {}
function PasswordFieldWrapper:setText(text) end
---@return string The plain text password (not masked).
function PasswordFieldWrapper:getText() end
--- Sets the "ghost text" (placeholder).
---@param text string
function PasswordFieldWrapper:setPrompt(text) end
function PasswordFieldWrapper:clear() end


---@class CheckBoxWrapper : UIComponent
local CheckBoxWrapper = {}
---@return boolean True if the box is checked, false otherwise.
function CheckBoxWrapper:isChecked() end
--- Manually sets the check state.
---@param checked boolean
function CheckBoxWrapper:setChecked(checked) end
--- Registers a callback triggered when the checkbox state changes.
---@param callback fun(checked: boolean) Receives the new state.
function CheckBoxWrapper:OnToggle(callback) end


---@class TextAreaWrapper : UIComponent
local TextAreaWrapper = {}
function TextAreaWrapper:setText(text) end
--- Appends text to the end of the existing content.
---@param text string
function TextAreaWrapper:appendText(text) end
---@return string
function TextAreaWrapper:getText() end
--- Controls whether text wraps to the next line or scrolls horizontally.
---@param wrap boolean
function TextAreaWrapper:setWrapText(wrap) end
function TextAreaWrapper:setEditable(editable) end


---@class ComboBoxWrapper : UIComponent
local ComboBoxWrapper = {}
--- Adds a new option to the dropdown list.
---@param item string
function ComboBoxWrapper:addItem(item) end
--- Removes all options from the list.
function ComboBoxWrapper:clearItems() end
---@return string|nil The currently selected item, or nil if nothing is selected.
function ComboBoxWrapper:getSelected() end
--- Selects an item by its numerical index (0-based).
---@param index number
function ComboBoxWrapper:select(index) end


---@class ProgressBarWrapper : UIComponent
local ProgressBarWrapper = {}
--- Sets the progress value.
---@param value number Between 0.0 (empty) and 1.0 (full).
function ProgressBarWrapper:setProgress(value) end
---@return number The current progress value.
function ProgressBarWrapper:getProgress() end
--- Sets the bar to indeterminate mode (animation loop without specific value).
function ProgressBarWrapper:setIndeterminate() end


---@class ImageWrapper : UIComponent
local ImageWrapper = {}
--- Loads and displays a new image from the specified file path.
---@param path string
function ImageWrapper:setImage(path) end
--- Controls whether the image aspect ratio is preserved during resizing.
---@param preserve boolean
function ImageWrapper:setPreserveRatio(preserve) end


-- ==========================================
-- WINDOW MANAGEMENT
-- ==========================================

---@class WindowWrapper
local WindowWrapper = {}

--- Adds a UI component to this window.
---@param component UIComponent
function WindowWrapper:add(component) end

--- Enables or disables the ability to minimize the window.
---@param allow boolean
function WindowWrapper:setMinimizable(allow) end

--- Enables or disables window resizing by the user.
---@param resizable boolean
function WindowWrapper:setResizable(resizable) end

--- Updates the window title.
---@param title string
function WindowWrapper:setTitle(title) end

--- Centers the window on the primary monitor.
function WindowWrapper:centerOnScreen() end

--- Closes the window.
function WindowWrapper:close() end


-- ==========================================
-- GLOBAL LIBRARY
-- ==========================================

--- The main MoonGUI entry point.
---@class MoonGUILib
---@field Window fun(self: MoonGUILib, title: string, width: number, height: number): WindowWrapper Creates a new application window.
---@field Button fun(self: MoonGUILib, text: string): ButtonWrapper Creates a clickable button.
---@field Label fun(self: MoonGUILib, text: string): LabelWrapper Creates a static text label.
---@field TextField fun(self: MoonGUILib, text: string): TextFieldWrapper Creates a single-line input field.
---@field PasswordField fun(self: MoonGUILib, text: string): PasswordFieldWrapper Creates a masked input field.
---@field CheckBox fun(self: MoonGUILib, text: string): CheckBoxWrapper Creates a toggleable checkbox.
---@field TextArea fun(self: MoonGUILib, text: string): TextAreaWrapper Creates a multi-line text editor.
---@field ComboBox fun(self: MoonGUILib): ComboBoxWrapper Creates a dropdown list.
---@field ProgressBar fun(self: MoonGUILib): ProgressBarWrapper Creates a progress bar.
---@field Image fun(self: MoonGUILib, path: string): ImageWrapper Loads an image from disk.
---@field Alert fun(self: MoonGUILib, message: string) Shows a system popup message.
local MoonGUILib = {}

-- ==========================================
-- GLOBAL INJECTION
-- ==========================================

--- The global MoonGUI instance provided by the engine.
---@type MoonGUILib
---@diagnostic disable-next-line: missing-fields
MoonGUI = {}