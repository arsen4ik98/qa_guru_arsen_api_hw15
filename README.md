
# Проект по автоматизации тестирования для сервиса [PetStore](https://petstore.swagger.io/)

## **Содержание:**
____

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Примеры автоматизированных тест-кейсов</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#console">Запуск из терминала</a>

* <a href="#allure">Allure отчет</a>

* <a href="#allure">Интеграция с Allure TestOps</a>

* <a href="#allure">Интеграция с Jira</a>

* <a href="#telegram">Уведомление в Telegram при помощи бота</a>

____
<a id="tools"></a>
## <a name="Технологии и инструменты">**Технологии и инструменты:**</a>

<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="images/logo/Github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="images/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>  
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>  
<a href="ht[images](images)tps://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>  
<a href="https://www.atlassian.com/ru/software/jira/"><img src="images/logo/Jira.svg" width="50" height="50"  alt="Jira"/></a>  
</p>

____
<a id="cases"></a>
## <a name="Примеры автоматизированных тест-кейсов">**Примеры автоматизированных тест-кейсов:**</a>
____
- ✓ *Поиск Контакты*
- ✓ *Переход на сайт Вакансии*
- ✓ *Поиск горячих вакансии*
- ✓ *Поиск удаленных вакансии*
- ✓ *Проверка поиска по странице*


____
<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="images/logo/Jenkins.svg" width="25"/></a><a name="Сборка"></a>Сборка в [Jenkins](https://jenkins.autotests.cloud/job/qa_guru_hw14_arsen/)</a>
____
<p align="center">  
<a href="https://jenkins.autotests.cloud/job/qa_guru_hw14_arsen/"><img src="images/screen/1.png" alt="Jenkins" width="950"/></a>  
</p>


### **Параметры сборки в Jenkins:**

- *browser (браузер, по умолчанию chrome)*
- *browserVersion (версия браузера, по умолчанию 114.0)*
- *browserSize (размер окна браузера, по умолчанию 1920x1080)*
- *baseUrl (адрес тестируемого веб-сайта)*
- *remoteUrl (логин, пароль и адрес удаленного сервера Selenoid)*

<a id="console"></a>
## Команды для запуска из терминала
___
***Локальный запуск:***
```bash  
gradle clean bi_test
```

***Удалённый запуск через Jenkins:***
```bash  
clean bi_test
-Dbrowser=${browser}
-Dversion=${browserVersion}
-DwindowSize=${browserSize}
-DremoteUrl=${remoteUrl}
```
___
<a id="allure"></a>
## <img alt="Allure" height="25" src="images/logo/Allure.svg" width="25"/></a> <a name="Allure"></a>Allure [отчет](https://jenkins.autotests.cloud/job/qa_guru_hw14_arsen/17/allure/)</a>
___

### *Основная страница отчёта*

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screen/2.png" width="850">  
</p>  

### *Тест-кейсы*

<p align="center">  
<img title="Allure Tests" src="images/screen/3.png" width="850">  
</p>

### *Графики*

  <p align="center">  
<img title="Allure Graphics" src="images/screen/4.png" width="850">

<img title="Allure Graphics" src="images/screen/5.png" width="850">  
</p>

____
<a id="telegram"></a>
## <img alt="Allure" height="25" src="images/logo/Telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота
____
<p align="center">  
<img title="Allure Overview Dashboard" src="images/screen/6.png" width="550">  
</p>

____
## <img alt="Selenoid" height="25" src="images/logo/Selenoid.svg" width="25"/></a> Примеры видео выполнения тестов на Selenoid
____
<p align="center">
<img title="Selenoid Video" src="images/video/1.gif" width="550" height="350"  alt="video">   
</p>
