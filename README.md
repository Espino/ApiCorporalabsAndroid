# ApiCorporalabsAndroid
ApiCorporalabsAndroid

El proyecto se desarrolló y probó con Android Studio Artic FOX 2020.3.1. Patch 2 ->con el compilador gradle 7.0.2 -> versión Kotlin 1.5.31 -> bajo javaversion 11
El proyecto utiliza la guía de arquitectura modular de CLEAN  SOLID del famoso tío Bob(Robert C.Martín) con 3 módulos, bajo un patrón de diseño MVVM que realiza la comunicación entre capas, con distintas fuentes de datos, de manera Reactiva (Patrón Observer – Patrón Repository – Patrón Factory (falta acabar de implementar)) y que utiliza como inyector de dependencias DaggerHilt(Patrón Singleton).
Durante el desarrollo he aplicado llamadas a la Api de servicio del NYTimes. 
Partes de la UI y el flujo del código de la aplicación:
1º -> Splash 
2º ->La aplicación en definitiva muestra una lista de artículos consumiendo el api de NYTimes.
3º ->Detalle del artículo.
-------------------Mejoras en futuro de la App----------------
1 -> Para la obtención y manejo de la lista de los datos actualizar a PagingData bajo el nuevo RemoteMediator que mejora el flujo para el control y fuente de los datos (cache y Remote)
2 -> Para la obtención y manejo de la lista de los datos está también NetworkBoundResource para el control la caché y fuente de datos.
3º -> Room para guardar la lista de los artículos en la parte de cache antes comentada.
4º ->Buscar Artículos
5º ->Cambio de Tema Light-Dark. 2 modos de hacerlo diferente en la App.
5º ->Añadir componente de Navegación en la parte inferior.
 
Bibliotecas de generación de código
-> Dagger2 para inyección de dependencias (debido a las issues procedentes de gradle y kotlin con las versiones más altas, no hemos podido usar Koin como inyector, debido a errores en compilación) -> Room para guardar la lista de Heroes y Favoritos.
Características del proyecto: - [x] Kotlin - [x] Dagger2Hilt (inyección de dependencia) - [x] Corutinas (extensión)(Flow) - [x] Glide y Picasso (biblioteca de imágenes) - [x] Navegación (componente de arquitectura de Android de jetpack) - [x] ViewBinding (componente de arquitectura de Android de jetpcak) - [x] ViewModel (componente de arquitectura de Android de jetpack) - [x] LiveData(componente de arquitectura de Android de jetpack)-  [x] Retrofit2 (para llamadas de servicio) - [x] okHttp (para la capa de red, interceptar registros http e interceptar para agregar apiKey a los parámetros de consulta para cada llamada de servicio)
  
El proyecto se ha dividido en 3 módulos (se puede realizar los módulos que desees, por features, core,…), que se enumeran a continuación en orden de arriba hacia abajo según el flujo de la aplicación:
1->Módulo Capa Presentación
1.	Aplicación: módulo de aplicación de Android como Capa Presentación que tiene el MainActivity y lo necesario para el DI, ViewModel, Adapter, Vista y Animaciones.
2.	Este módulo tiene el componente ViewModel que sobrevive a los cambios del ciclo de vida y proporciona la capa de interfaz de usuario con la nueva PagingData para que los elementos a través de LiveData se vinculen con la interfaz de usuario. Utiliza corrutinas para realizar las operaciones scope.
3.	Es el módulo de funciones y contiene toda la lógica de la interfaz de usuario + diseños para las dos pantallas (lista y detalle).
4.	Escucha los diversos estados de la red que el módulo de 'datos' emite y reacciona a ellos actualizando la interfaz de usuario de manera adecuada.
5.	La lista paginada se utiliza para actualizar el adaptador que respalda la vista de reciclador. Hay dos vistas y se generan dos enlaces utilizando la biblioteca de enlace de datos para enlazar los diseños de la interfaz de usuario con los fragmentos. Además, debido a que una vista de reciclador se utiliza, hay un ItemViewBinding generado para las vistas de elementos en la vista de reciclador.
2->Módulo Capa Dominio (Lógica y reglas de negocio)
1.	Módulo de biblioteca de Kotlin. Este es el núcleo de la aplicación que contiene los casos de uso de la aplicación (interactors) y los modelos o entidades.
2.	Si es necesario, podemos tener varios módulos de dominio para cada característica para escalar el equipo. según las necesidades de la empresa u organización. Esta capa tiene la implementación no específica de Android y define contratos para la capa superior ('presentación') e inferior ('datos') Llama a la capa del módulo de 'datos' para buscar o activar el flujo reactivo de los datos desde la capa inferior (datos) hasta la parte superior (UI)…
3.	Utiliza Flow y Courutinas.
4.	Tenemos el repositorio donde se declaran los métodos que usan los casos de uso.
3->Módulo Capa Data
1.	Módulo de biblioteca de Android que contiene los servicios.
2.	Esta es una abstracción que sigue el patrón Repository. Oculta la fuente real de los datos
3.	Esta capa implementa el contrato de interfaz de Repository definido en la capa del módulo de 'datos'. 
4.	Utiliza la API de Retrofit y okHttp como cliente para las llamadas a la API. Las claves de API (públicas y privadas) se proporcionan en el archivo gradle.properties y están disponibles como BuildConfig definido en el archivo gradle del módulo 'aplicación'. Las propiedades requeridas para la actualización se proporcionan a través de Dagger desde el módulo 'aplicación' mediante inyección de dependencia. 


