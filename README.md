# TransferList Challenge

## Build

The straightforward way to build the project is to:

- Install Android Studio or its commandline tools
- Run the project by the AS or `./gradlew assembleDebug` for getting a debug `.apk`

## Preview

Always there's an **.apk** and application's **screen record** are
in [`/preview`](https://github.com/beigirad/TransferListChallenge/tree/develop/preview) directory.

#### Or

You can access to latest `.apk` of each PR or commit on `develop` branch:

- Go to in [actions tab](https://github.com/beigirad/TransferListChallenge/actions).
- Open the suit workflow-run that has completed
- Download `zipped-apk` in artifacts section
- Extract it from the zip file. It's `.apk`

## Notes

1. Project tends to use the cutting edge technologies that might be experimental in the Android
   ecosystem (such as `version catalog`).
   In order to achieve this, *Canary Android studio (Hedgehog)* is used which supports them.
2. This project also tends to represent my following skills: Git, Git flow, automation, code style,
   android new tools
3. Pull requests and respective branches will not be removed
4. Project uses a combination of Google's recommended architecture and `Clean` arch. `data`
   contains `domain` repositories but domain models has moved into `models`.
   And all of them goes into `/core` sub-directory. Feature modules are subset of `/feature`
   directory.
5. Some of generic classes has their own responsibility and definition on their JavaDoc in
   source-code

## Tasks

- [x] Name of the app: `TransferList Challenge`
- [x] Provide a simple Git flow
- [x] Setup the project structure for an initial commit
- [x] Determine the package structure and the architecture of the project
- [x] Libraries and tools
    - [x] Choose language: `Kotlin`
    - [x] Choose UiKit: `Native`
    - [x] Choose DI library: `Hilt`
    - [x] Choose Architecture: `Clean + MVVM`
    - [x] Async/Event: `Kotlin coroutines, Flow`
- [ ] Testing (WIP)
    - [ ] Choose library:
    - [ ] Implement sample tests
    - [ ] Implement sample Ui tests
- [x] CI/CD
    - [x] Choose the platform: `Github actions`
    - [x] Build the app
    - [ ] Run tests
- [ ] Generate the icon
    - [ ] Support android 13 colored icons
- [ ] Use `detekt` for linting
- [ ] Internal docs
- [ ] Resolve all TODOs and FIXMEs
- [ ] Finalize everything and clean up
- [ ] Record a showcase video/gif and add to this file
