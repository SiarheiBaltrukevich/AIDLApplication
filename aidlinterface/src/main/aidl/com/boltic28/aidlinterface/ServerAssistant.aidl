package com.boltic28.aidlinterface;

   // В AIDL поддерживаются следующие типы данных:
   // Все примитивы из Java (int, long, char, boolean и т.д)
   // Массивы примитивов (int[] и т.д.)
   // Строковые типы CharSequence и String
   // List<T> содержащий данные типа из этого списка
   // Map<*, *> содержащий данные типа из этого списка, но без параметризации! Т.е не получится написать Map<String, Integer> - компилятор выдаст ошибку
   // Parcelable классы, в том числе Bundle


interface ServerAssistant {
    int numberOfCars();
    int numberOfBuses();
    int numberOfBikes();
    int numberOfBoats();
}