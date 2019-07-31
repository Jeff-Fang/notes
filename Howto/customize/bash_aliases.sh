# Mac
alias chrome='open -a /Applications/Google\ Chrome.app'
alias safari='open -a safari'
alias mysql=/usr/local/mysql/bin/mysql
alias mysqladmin=/usr/local/mysql/bin/mysqladmin


# Ubuntu
alias open='screen nautilus'
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

export nut=$HOME/Nutstore
  export nutmir=$nut/MiRFilesSync
  export nutdoc=$nut/Nutstore
    export backup=$nutdoc/Backup

export mir=$HOME/MiRWorkspace
  export mirdev=$mir/Dev
  export mircf=$mir/CustomerFiles


# Help fuction
function mark {
  export $1=$(pwd);
}

alias sshaws='ssh -i $backup/server/AWS-EC2.pem'
alias sshali='ssh -i $backup/server/AliMiRSim.pem'

alias mirunzip='~/MiRWorkspace/Dev/MiR_unzip.bash'
