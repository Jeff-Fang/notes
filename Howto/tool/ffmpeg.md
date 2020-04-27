

```bash
# Convert Video
ffmpeg -i input.wmv -c:v libx264 -preset ultrafast output.mp4

# Reduce file size by lowering rate
ffmpeg -i MiR_Fleet_CN.mp4 -vcodec libx264 -strict -2 -crf 24 output.mp4

# Extract subtitle
ffmpeg -i input.mkv -map 0:s:0 subs.srt

```

