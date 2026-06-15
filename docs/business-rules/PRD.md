# imin_vice_screen — Business Rules (Living PRD)

Updated: 2026-06-15 – initial extraction during toolchain modernization.

## Purpose

Flutter plugin (Android-only) for iMin smart POS devices that have a second
display. It supports two distinct hardware families:

1. **Full secondary screen (e.g. D4)** — a real second `Display` rendered with a
   Flutter UI via Android `Presentation`, shown as an overlay
   (requires the `SYSTEM_ALERT_WINDOW` "draw over other apps" permission).
2. **Mini LCD customer display (e.g. Falcon 1 / D1)** — a small SPI text/bitmap
   display driven through iMin's `ILcdManager` (`sendLCDString`, `sendLCDBitmap`,
   multi-line, double-line, etc.).

## Main ↔ secondary communication

- The app's `main()` inspects the default route name; `"viceMain"` boots the
  secondary-screen entrypoint, anything else boots the primary UI.
- Two method channels bridge the screens: `imin_vice_screen` (main) and
  `imin_vice_screen_child` (vice). Each side exposes a broadcast `Stream` of
  incoming `MethodCall`s, and `sendMsgToViceScreen` / `sendMsgToMainScreen` send
  messages across.

## Capabilities (Dart API)

- `isSupportMultipleScreen`, `checkOverlayPermission`, `requestOverlayPermission`
- `doubleScreenOpen` / `doubleScreenCancel` — show/hide the Flutter secondary screen
- `sendMsgToViceScreen` / `sendMsgToMainScreen` — cross-screen messaging
- `sendLCDCommand`, `sendLCDString`, `sendLCDMultiString`, `sendLCDDoubleString`,
  `sendLCDBitmap`, `setTextSize` — mini-LCD control
- `sendLCDBitmap` also supports loading from a URL (fetched via Glide).

## Permissions

The library requires exactly two permissions, both code-backed:
- `SYSTEM_ALERT_WINDOW` — draw the secondary screen as an overlay.
- `INTERNET` — fetch a bitmap from a URL for the LCD.

No external-storage permission is needed; image work uses app-private cache dirs
and in-memory bitmaps. (`MANAGE_EXTERNAL_STORAGE` was previously declared in error
and blocked Play Store publication of consuming apps — removed 2026-06-15.)

## Known constraints

- Android only; no iOS.
- The mini-LCD bitmap path currently depends on a bundled native FreeImage `.so`
  that is not 16 KB page-size compliant (Android 15 / API 35). Tracked separately —
  see the PR2 spec for the pure-Kotlin replacement.
