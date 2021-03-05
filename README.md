# vst3kotlin.sample
vst3kotlinを使ったサンプルプロジェクトです。

## How to Build
このサンプルプロジェクトをビルドする前に [Durun/vst3kotlin](https://github.com/Durun/vst3kotlin) をビルドする必要があります。
以下の手順でビルド生成物をMavenLocalにpublishしてください。
```shell
git clone https://github.com/Durun/vst3kotlin.git
cd vst3kotlin

./gradlew publishToMavenLocal # for Windows> .\gradlew publishToMavenLocal
# OutOfMemoryErrorが出る場合は、何回か試すと成功することがあります。
```

続いて、このサンプルプロジェクトをビルドします。
```shell
git clone https://github.com/Durun/vst3kotlin.sample.git
cd vst3kotlin.sample
./gradlew build # for Windows> .\gradlew build
```

実行可能ファイルが`build/bin`以下に生成されたはずです。

## How to Run
第1引数に`*.vst3`フォルダまたはファイルを指定してください。

実行すると、プラグイン画面が表示されると思います。
表示されなければ、このサンプルはそのプラグインに対応していない可能性があります。

画面のパラメータを操作すると、内容が標準出力されます。

無操作状態がしばらく続くと終了します。(これは私がウィンドウの閉じるボタンで終了する実装をきちんとやっていないからです。)

Windowsでの実行例は以下の通りです。
```shell
sample.exe "C:\Program Files\Common Files\VST3\FabFilter Pro-Q 3.vst3"
```