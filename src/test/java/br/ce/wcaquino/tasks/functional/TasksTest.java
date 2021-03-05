package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://172.17.0.1:17701/wd/hub"), cap);
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			
			//Clicar em Add
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//Escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/03/2021");
			
			//Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		
		} finally {
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naodeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {	
			//Clicar em Add
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//Escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/03/2021");
			
			//Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally {
			//fechar o browser
			driver.quit();
		}
	} 	
	
	@Test
	public void naodeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {	
			//Clicar em Add
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
						
			//Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {
			//fechar o browser
			driver.quit();
		}
	}
	
	public void deveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {	
			//Clicar em Add
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//Escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/03/2021");
			
			//Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			//fechar o browser
			driver.quit();
		}
	}
}