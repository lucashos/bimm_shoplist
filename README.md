# Sake Shop

An Android application developed in Kotlin using Jetpack Compose, modular architecture, and Clean Architecture principles.

The app provides a list of sake shops and allows navigation to detailed information about each shop.

## Technologies Used

- Kotlin
- Jetpack Compose
- Navigation Compose with Compose Destinations
- Modular Architecture
- MVVM + Clean Architecture
- Coroutines + Flow
- Hilt
- Jetpack Compose Testing
- JUnit, Turbine, Coroutine Test

## Best Practices Applied

- Full modularization
- Scalable architecture with Clean Architecture principles
- Dependency injection with Hilt
- Navigation decoupled via Compose Destinations
- Reactive flows with Kotlin Flow
- High testability for both UI and business logic


## Architectural Considerations

This project follows Clean Architecture and Modular Design principles to ensure scalability, maintainability, and testability.

- Modular separation enables independent development, testing, and deployment.
- Domain is completely decoupled from frameworks, UI, and infrastructure.
- Data providers are swappable without impacting business logic.
- The Design System is centralized and versioned for consistency across features.
- HTTP requests are intentionally not allowed.
  - The architecture enforces the use of local providers, assets, or secure communication protocols only.

## Project Architecture

```plaintext
:app            → Application module. Handles navigation, DI setup, and app initialization.
:core           → Core utilities, constants, and shared components.
:domain         → Business rules, entities, and use cases, repository especifications.
:data           → Data layer, repositories implementations, API services, local databases, and data sources.
:designsystem   → Design system including colors, typography, and UI components.
:feature        → UI features, screens, state management, and ViewModels.
```

- Fully modularized by responsibility.
- Clean separation between domain, data, UI, and design system.
- Promotes high testability, scalability, and maintainability.

## Features

- Display a list of sake shops
- Show detailed information including address, rating, website, and Google Maps link
- Navigation between list and details screens
- Fully declarative UI with Jetpack Compose


## Testing Strategy

### Unit Tests
- ViewModels

```bash
./gradlew testDebugUnitTest
```

### UI Tests (Compose)
- Performed in the `:app` module using `AndroidComposeTestRule`

```bash
./gradlew connectedAndroidTest
```

## Design System

- Centralized theme, typography, and color definitions in the `:designsystem` module

## Folder Structure Example

```plaintext
.
├── README.md
├── app
│     ├── MainActivity.kt
│     ├── SakeShopApplication.kt
│     └── navigation
│         └── MainNavGraph.kt
├── core
│     └── di
│         └── CoroutineModule.kt
├── data
│     ├── adapters
│     │     └── CoordinatesAdapter.kt
│     ├── datasource
│     │     └── SakeShopDataSource.kt
│     ├── di
│     │     └── DataModule.kt
│     ├── mappers
│     │     ├── CoordinatesMapper.kt
│     │     └── SakeShopMapper.kt
│     ├── model
│     │     ├── CoordinatesTO.kt
│     │     └── SakeShopTO.kt
│     ├── providers
│     │     ├── JsonProvider.kt
│     │     └── ShopListJsonProvider.kt
│     └── repository
│         └── SakeShopRepositoryImpl.kt
├── designsystem
│     └── designsystem
│         ├── Color.kt
│         ├── Dimens.kt
│         └── Theme.kt
├── domain
│     └── domain
│         ├── model
│         │     ├── Coordinates.kt
│         │     └── SakeShop.kt
│         ├── repository
│         │     └── SakeShopRepository.kt
│         └── usecase
│             └── ListSakeShopsUseCase.kt
├── feature
│     ├── androidTest
│     │     └── sakeshop
│     │         ├── TestActivity.kt
│     │         └── shoplist
│     │             └── ShopListScreenTest.kt
│     ├── main
│     │     ├── navigation
│     │     │     └── FeaturesNavGraph.kt
│     │     ├── shopdetails
│     │     │     ├── ShopDetailsScreen.kt
│     │     │     ├── ShopDetailsState.kt
│     │     │     └── ShopDetailsViewModel.kt
│     │     └── shoplist
│     │         ├── ShopListScreen.kt
│     │         ├── ShopListState.kt
│     │         └── ShopListViewModel.kt
│     └── test
│         └── shoplist
│           └── ShopListViewModelTest.kt

```

### Next steps
- Snapshot Testing
  -  Introduce snapshot testing using Paparazzi for key UI components and screens.
- Expand Testing Coverage
  - Increase unit and UI test coverage across all modules (domain, data, feature).
  - Add integration tests.
- Migrate from local JSON to API