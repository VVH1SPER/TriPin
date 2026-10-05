# Photo Organizer

This project is a native Android application scaffold based on the technical specification in the workspace.

## Stack

- Kotlin
- Jetpack Compose
- Room database
- CameraX
- DataStore
- Navigation Compose
- Coil

## Structure

- `app/src/main/java/com/photoorganizer` – UI, navigation, screens, and app entry point
- `data/database` – Room entities and DAO layer
- `model` – domain models

## Notes

This source is intentionally prepared as a starting implementation for the specification and still needs final feature expansion for:

- camera flow and preview
- folder tree management
- tags and search logic
- export features
- full UI state management

## Open in Android Studio

1. Open the folder `PhotoOrganizerApp` in Android Studio.
2. Let Gradle sync the project.
3. Build and run on a device or emulator with Android 10+.
