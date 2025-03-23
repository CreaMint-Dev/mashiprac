
# MashiPractice
動作条件: paper spigot 1.8.8
概要: 1vs1やPartyを組んでPvPをするプラグイン
Java version: 17
機能:
Ranked Queue (Rankedのみ(duelやpartyやtournamentは除く)
Party Fight (パーティー内で対戦する)
Tournament (1vs1や2vs2ができるトーナメント)
Event (HypixelのTNTTagと一緒のものを実装したい)
Duel (/duel <player>で対戦申込が送れる)
Settings (Scoreboardのオンオフや、Duel Requestsのオンオフを設定可能)
Kit Editor (対戦時のインベントリの配置を編集するもの)

コマンド:
"/mashiprac setspawn" コマンドを打った位置にロビースポーン位置を設定
"/event" 開催するイベント選択のGUIが開く（実行するには"mashiprac.event"というpermissionが必要）
具体的な動作：コマンドを打つと開催するイベントを選択するGUIが表示される。イベントをクリックすると、イベントが開かれ、チャットでBroadcastされ、サーバーに参加している全プレイヤーにイベントのお知らせをする。なおそのチャットをクリックするとイベントに参加できるようにする。（対戦中やパーティーに参加している場合は参加できない）イベントはとりあえずTournament、TNTTagの２つを実装する、なおイベントは２つ同時に開く事ができない。
"/mashiprac arenasworld" Mapsワールドのx:0 y100 z:0にテレポートするコマンド mashiprac.adminというpermissionが必要
"/mashiprac spawn" ロビースポーンにテレポートできる。エラーハンドリングを忘れずに。mashiprac.adminというpermissionが必要

プレイヤー用コマンド:
"/duel <player>" 指定のプレイヤーに1vs1申込ができる。
具体的な動作：コマンドを打つとるKitリストのGUIを開く。左クリックするとそのKitで送る。右クリックをするとさらにマップ選択するGUIに飛び、いずれかをクリックするとその選択したKitとマップで対戦申込を送る。
"/spec <player>" "/spectate <player>" 指定のプレイヤーが対戦中の場合観戦できる。
具体的な動作：プレイヤーにテレポートする。対戦中のプレイヤーからはパケットを使って感染中のプレイヤーを見れないようにする
"/stats <player>" 指定のプレイヤーのStatsを表示するGUIを出す。<player>に何も入力されていない場合は自分のstatsを表示する。そこではマッチに勝利した数、敗北した数、キル数を表示する
"/l" 対戦中に使えるコマンド、ロビースポーンに戻ることができる。このとき対戦から離脱すると死亡判定になる。
"/event join" 開催中のイベントに参加するコマンド

パーティーコマンド:
"/party" "/p" パーティーコマンドのHelpをチャットに表示
以下のコマンド"/party" "/team"の場合でも同じ動作をする
"/p create" パーティーを作成するコマンド
"/p invite <player>" パーティーにプレイヤーを招待するコマンド
"/p setlimit <limit>" パーティーの参加人数の制限を設定するコマンド、デフォルトは100 (このコマンドはパーティーリーダーでないと使用できない）
"/p promote <player>" パーティーリーダーを指定のプレイヤーに変更する（同じパーティーに参加しているプレイヤー以外にはできない）
"/p leave" パーティーから離脱するコマンド（パーティーリーダーが離脱した場合はDisbandし、パーティーに参加していたプレイヤーを全員離脱させる）
"/p join <player>" パーティーに参加するコマンド　すでにパーティーに参加している場合はできない
"/p open" パーティーをOpenにするコマンド　これによりinviteをしなくてもjoinコマンドで参加できる。 (このコマンドはパーティーリーダーでないと使用できない）
"/p announce" サーバー参加者全員に見えるようにチャットでパーティーをBroadcastする。チャットをクリックでパーティーに参加できる。
"/p kick <player>"　自分のパーティーに参加している指定のプレイヤーを離脱させるコマンド (このコマンドはパーティーリーダーでないと使用できない）
"/p chat <message>" パーティーに参加しているプレイヤー内だけにみることができるチャット

Kit管理用コマンド mashiprac.kitというpermissionが必要
/kit create <kitname>　Kitを作成するコマンド
/kit delete <kitname>　Kitを削除するコマンド
/kit setinv <kitname>　Kitのデフォルトインベントリを設定するコマンド
/kit loadinv <kitname>　Kitのインベントリを自分のインベントリにロード
/kit seticon <kitname>　KitのQueueやKitEditorなどに表示される時のアイテムアイコン、コマンド実行時の手持ちのアイテムをアイコンにする（表示名も）
/kit settings <kitname> Kitの詳細のルールを設定できるGUIが開かれる。空腹ゲージが減るかどうか　ダメージを喰らわないようにするか（殴れる）、ネームタグのところのヘルスバーの表示、体力の自然回復など、enderpearlのクールダウンなど、何点先取か、ゲームルールなど(Bridges, Sumo, BedFight, Boxing)
/kit list　Kitのリストをチャットへ表示

ゲームルール説明:
Bridges　相手のエンドゲートに入ると勝利　どちらかがエンドゲートに入って勝利するまではリスポーンできる。リスポーンクールダウンは4秒
Sumo　死亡または水に触れると敗北
BedFight　自分のベッドが破壊されるとリスポーンができなくなる。その上で死亡すると敗北
Boxing 相手を100回殴ると勝利

Arena管理用コマンド mashiprac.arenaというpermissionが必要
/arena create <arenaname>　アリーナを作成するコマンド
/arena delete <arenaname>　アリーナを削除するコマンド
/arena teleport <arenaname>　アリーナのpos1の座標にテレポートする　ワールドがコマンドを実行するワールドと違う可能性があるので注意
/arena kits <arenaname>　アリーナを使用できるKitを設定できる　何も設定されていない場合は全部のKitが使用できる
/arena clearkits <arenaname>　アリーナを使用できるKitをリセット
/arena resetaftermatch <arenaname>　アリーナを対戦で使用後、"/arena c1" "/arena c2"で設定したブロック内をリセットするかどうか　デフォルトはオフ（BuildUHCなどブロックを配置するゲームのため）
/arena pos1 <arenaname>　対戦時のプレイヤー1のスポーン位置を設定できる
/arena pos2 <arenaname>　対戦時のプレイヤー2のスポーン位置を設定できる
/arena c1 <arenaname>　アリーナを範囲指定できる　指定１
/arena c2 <arenaname>　アリーナを範囲指定できる　指定２（指定１と指定２内が範囲になる）
/arena list　アリーナリストをチャットに表示
/arena seticon <arenaname>　アリーナの/duelなどで対戦を送る際に表示されるアリーナに使われるアイテムアイコンを設定できる、コマンド実行時の手持ちのアイテムをアイコンにする（表示名も）

サーバー参加時:
Serverに参加すると"/mashiprac setspawn"で設定したスポーン位置にスポーン
スポーンアイテムが渡される
-スロット1に対戦Queue参加用のダイヤの剣（名前は"&8Ranked Queue &7(Right-Click)")
-スロット9にKitEditor用の本（名前は"&8Edit Kits &7(Right-Click)")

対戦Queue参加用のダイヤの剣の説明
右クリックするとKit選択のGUIが開かれる、KitをクリックするとQueueに参加しインベントリが切り替わり、スロット１に赤色の染料をもたせる、その染料を右クリックするとQueueから抜け、もとのスポーンアイテムに戻る。

KitEditor用の本の説明
右クリックするとKit選択のGUIが開かれる、KitをクリックするとGUIを開いたままインベントリにそのKitの装備がロードされる。好きなように配置を変更、アイテムを削除したりしてインベントリをSaveできる。GUIに指定のアイテムを掲示しインベントリに運んで持ってくる事ができる。（後述）GUIには上に本を5個横に並べて表示し、5個のインベントリを保存できるようにする（左クリックで保存、右クリックでリセット）。KitEditorを閉じるためのCancelボタンも用意する。

Queueシステムの解説：
同じKitに2人がQueueに入ると、マッチングし、アリーナにテレポートされ対戦が開始される。
テレポート後、KitEditorでKitを保存している場合はインベントリに対応した本を持たせる（クリックでそのインベントリをロード）。
なお、テレポート後に５カウントが始まり、5カウント後試合が開始する。5カウントの間はプレイヤーを攻撃できない。
（マッチング後の挙動は/kit settingsで設定した動作によって変更する）

サーバーを抜けた際：
パーティーから抜ける（パーティーリーダーの場合はDisband）
マッチ中の場合は死亡判定にし、マッチから離脱
etc...　エラーハンドリングをしっかりする
