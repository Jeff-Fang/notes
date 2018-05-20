# tmux cheatsheet

## Prompt I
|       key        |                    feature                     |
|------------------|------------------------------------------------|
| tmux             | Start new session from prompt                  |
| tmux new -s name | Start new session with name 'name' from prompt |
| tmux a           | Attach session from prompt                     |
| tmux ls          | List session from prompt                       |

## Sessions I
|   key   |    feature    |
|---------|---------------|
| C-bs    | List sessions |
| C-b$    | Name session  |
| C-b:new | New session   |

## Windows I
| key  |     feature     |
|------|-----------------|
| C-bc | Create window   |
| C-bw | List windows    |
| C-bn | Next window     |
| C-bp | Previous window |

## Windows II
| key  |   feature   |
|------|-------------|
| C-bf | Find window |
| C-b, | Name window |
| C-b& | Kill window |

## Panes I
| key  |      feature      |
|------|-------------------|
| C-b% | Vertical split    |
| C-b" | Horizontal split  |
| C-bo | Swap panes        |
| C-bq | Show pane numbers |
| C-bx | Kill pane         |

## Panes II
| key  |            feature             |
|------|--------------------------------|
| C-b  | Toggle between layouts (space) |
| C-b{ | Move current pane left         |
| C-b} | Move current pane right        |
| C-bz | Toggle pane zoom               |

## Misc I
|            key            |                  feature                   |
|---------------------------|--------------------------------------------|
| tmux a -t name            | Attach session to named 'name' from prompt |
| tmux kill-session -t name | Kill session named 'name' from prompt      |
| C-bd                      | Detach                                     |
| C-bt                      | Big clock                                  |
| C-b?                      | List shortcuts                             |
| C-b:                      | Prompt                                     |