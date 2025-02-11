# 1. Описание приложения: *Peer-To-Peer Bazaar*
   ## Функциональность:<br/>
   Приложение соединяет мелких производителей с покупателями, позволяя им торговать свежими продуктами напрямую.
   Использование **_квадрокоптеров_** ![picture](https://upload.wikimedia.org/wikipedia/commons/3/35/Onyxstar_Fox-C8_XT_xender_360.jpg) для доставки товаров, что позволяет быстро и эффективно доставлять продукты на большие расстояния 
   в труднодоступные районы.
## 2. Информация, принимаемая от пользователя<br/>
   ### Пользовательская информация:<br/>
   1. Имя: для идентификации пользователя.
   2. Электронная почта: для связи и уведомлений.
   3. Телефон: для подтверждения заказа и связи с продавцом.
   4. Заказы: связь пользователя со всеми осуществленными сделками (заказами).
   5. Способы оплаты : банковская карта, крипто-кошельки, осуществляются также бартерные операции.
   6. Описание товара: информация о продукте, включая название, цену, количество и фотографии.
   7. Статус: указание на то, является ли пользователь продавцом или покупателем.
   8. Рейтинг: оценка пользователя после каждой проведенной сделки.

## 3. Способы приема информации


  ### Веб-приложение:
- Пользователи могут регистрироваться и авторизовываться через веб-интерфейс.
- Для авторизованных пользователей достаточно загрузить фото товара и указать геолокацию.
- Форма для ввода информации о товаре, включая описание, цену и статус (купить/~~продать~~).
 ## 4. Хранение информации
   ### Серверное хранилище:
- Данные пользователей и товары будут храниться в реляционной базе данных (например, PostgresSQL или MySQL).
- Структура базы данных может включать следующие таблицы:<br/>
   1. Users: информация о пользователях (идентификатор, имя, email, телефон, рейтинг, набор заказов, статус).
   2. Products: информация о товарах (идентификатор, название, описание, цена, фото, идентификатор продавца).
   3. Orders: информация о заказах (идентификатор, идентификатор покупателя, идентификатор товара, статус заказа, дата заказа).
   4. Payments: информация о платежах (идентификатор, идентификатор заказа, сумма, статус платежа).

## 5. Взаимодействие с другими сервисами
   #### Платежная система:
   Интеграция с платежными системами (PayPal, CryptoService) для обработки платежей между покупателями и продавцами.
   Обработка транзакций и управление возвратами.
   #### Служба квадрокоптеров:
   Интеграция с API службы доставки квадрокоптеров для управления логистикой.
   Отслеживание статуса доставки и уведомление пользователей о времени прибытия.
   ## Заключение
   **Приложение "Peer-To-Peer Bazaar" имеет потенциал стать удобным и 
   эффективным инструментом для соединения мелких производителей с покупателями,
   обеспечивая свежими продуктами и быструю доставку в труднодоступные районы.**
