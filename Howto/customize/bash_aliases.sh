# TODO - Select OS
# Mac
alias chrome='open -a /Applications/Google\ Chrome.app'
alias safari='open -a safari'
alias mysql=/usr/local/mysql/bin/mysql
alias mysqladmin=/usr/local/mysql/bin/mysqladmin


# Ubuntu
alias open='nautilus'
alias chrome='google-chrome'


# Shortcut structure
export desk=$HOME/Desktop
export doc=$HOME/Documents

export app=$HOME/Application
  export venv=$app/venv

export repo=$HOME/Repo
  export note=$repo/notes
    export howto=$note/Howto
    export code=$note/Code
      export test=$code/test
      export kata=$code/kata

export work=$HOME/Workspace
  export uda=$work/Udacity
  export mir=$work/mir

export nut=$HOME/Nutstore
  export nutmir=$nut/MiRFilesSync
  export nutdoc=$nut/Nutstore
    export backup=$nutdoc/Backup
    export memo=$nutdoc/memo.md



# Help fuction
function mark {
  export $1=$(pwd);
}

alias sshaws='ssh -i $backup/server/AWS-EC2.pem'
alias sshali='ssh -i $backup/server/AliMiRSim.pem'

alias mirunzip='~/MiRWorkspace/Dev/MiR_unzip.bash'

# Ignore case
# https://askubuntu.com/questions/87061/can-i-make-tab-auto-completion-case-insensitive-in-bash
# If ~./inputrc doesn't exist yet, first include the original /etc/inputrc so we don't override it
if [ ! -a ~/.inputrc ]; then echo '$include /etc/inputrc' > ~/.inputrc; fi

# Add option to ~/.inputrc to enable case-insensitive tab completion
echo 'set completion-ignore-case On' >> ~/.inputrc
