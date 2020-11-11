if [[ $(uname) == 'Darwin' ]]; then
  # echo "running on a mac"
  alias safari='open -a safari'
  alias chrome='open -a /Applications/Google\ Chrome.app'
  # alias mysql=/usr/local/mysql/bin/mysql
  # alias mysqladmin=/usr/local/mysql/bin/mysqladmin
elif [[ $(uname) == 'Linux' ]]; then
  # echo "running on a linux"
  function open {
    nautilus $1 1>/dev/null 2>/dev/null &
  }
  # alias open='nautilus 1>/dev/null 2>/dev/null &'
  alias chrome='google-chrome 1>/dev/null 2>/dev/null &'
fi


# Shortcut structure
export desk=$HOME/Desktop
export mirlog=$HOME/Downloads/mirlogs
export doc=$HOME/Documents
    export memo=$doc/memo.md
    export backup=$doc/Backup

export app=$HOME/Application
  export venv=$app/venv

export vm=$HOME/VMs

export repo=$HOME/Repo
  export note=$repo/notes
    export howto=$note/Howto
      export code=$note/Code
        export test=$code/test
        export kata=$code/kata

export work=$HOME/Workspace
  export mir=$work/mir
  export mir2=$work/mirws2/mir
  export mir3=$work/mirws3/mir
  export tool=$work/tools
  export mwf=$work/mir_wifi
  export mwfhap=$mwf/mir_wifi_hostap
  export mwfapi=$mwf/mir_wifi_api
  export mwfctl=$mwf/mir_wifi_control
  export mwftst=$mwf/mir_wifi_system_tests


# Helper fuction
function mark {
  export $1=$(pwd);
}


# Ignore case
# https://askubuntu.com/questions/87061/can-i-make-tab-auto-completion-case-insensitive-in-bash
# If ~./inputrc doesn't exist yet, first include the original /etc/inputrc so we don't override it
if [ ! -a ~/.inputrc ]; then echo '$include /etc/inputrc' > ~/.inputrc; fi

# Add option to ~/.inputrc to enable case-insensitive tab completion
echo 'set completion-ignore-case On' >> ~/.inputrc


# Setup TTY colors
# https://askubuntu.com/questions/147462/how-can-i-change-the-tty-colors
if [ "$TERM" = "linux" ]; then
    echo -en "\e]P0232323" #black
    echo -en "\e]P82B2B2B" #darkgrey
    echo -en "\e]P1D75F5F" #darkred
    echo -en "\e]P9E33636" #red
    echo -en "\e]P287AF5F" #darkgreen
    echo -en "\e]PA98E34D" #green
    echo -en "\e]P3D7AF87" #brown
    echo -en "\e]PBFFD75F" #yellow
    echo -en "\e]P48787AF" #darkblue
    echo -en "\e]PC7373C9" #blue
    echo -en "\e]P5BD53A5" #darkmagenta
    echo -en "\e]PDD633B2" #magenta
    echo -en "\e]P65FAFAF" #darkcyan
    echo -en "\e]PE44C9C9" #cyan
    echo -en "\e]P7E5E5E5" #lightgrey
    echo -en "\e]PFFFFFFF" #white
    clear #for background artifacting
fi
