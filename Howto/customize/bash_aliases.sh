# TODO - Select OS
# Mac
alias chrome='open -a /Applications/Google\ Chrome.app'
alias safari='open -a safari'
#alias mysql=/usr/local/mysql/bin/mysql
#alias mysqladmin=/usr/local/mysql/bin/mysqladmin


# Ubuntu
alias open='nautilus'
alias chrome='google-chrome'


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
  export mir2=$work/mir2
  export mir3=$work/mir3
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


# Source scripts
source $tool/code/mir_log_unzip.bash


# Ignore case
# https://askubuntu.com/questions/87061/can-i-make-tab-auto-completion-case-insensitive-in-bash
# If ~./inputrc doesn't exist yet, first include the original /etc/inputrc so we don't override it
if [ ! -a ~/.inputrc ]; then echo '$include /etc/inputrc' > ~/.inputrc; fi

# Add option to ~/.inputrc to enable case-insensitive tab completion
echo 'set completion-ignore-case On' >> ~/.inputrc
