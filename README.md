# 🧬 Covid-19 Detection App — VoiceUp

An Android application for Covid-19 detection developed as part of the VoiceUp research project. Uses machine learning to analyze voice samples and detect potential Covid-19 indicators.

## Background

This app was built during the early Covid-19 pandemic (April 2020) as part of the VoiceUp initiative — a research effort exploring non-invasive, voice-based screening for Covid-19 detection using ML models.

## Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Kotlin |
| ML | TensorFlow Lite / on-device inference |
| Audio | Android AudioRecord API |
| UI | Android XML layouts |

## Features

- **Voice recording** — captures audio samples via device microphone
- **On-device ML inference** — runs Covid detection model locally (no server required)
- **Result display** — shows detection confidence score
- **Research data** — anonymized sample collection for model improvement

## Getting Started

### Prerequisites
- Android Studio
- Android SDK 21+

### Setup

```bash
git clone https://github.com/eranCat/Covid-Detection-app---VoiceUp.git
```

1. Open in Android Studio
2. Build & run on a physical device (microphone required)

> ⚠️ This app was built as a research prototype in 2020. It is not intended for medical diagnosis.

## Author

**Eran Karaso** — [Portfolio](https://erancat.github.io/portfolio-site) · [GitHub](https://github.com/eranCat)
