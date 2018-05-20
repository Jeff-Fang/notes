# LaTeX 使用备忘
### 生成目录：
```latex
\tableofcontents
```

### 画图
在LaTeXiT中设置头：
```latex
\usepackage{pgfplots}
\pgfplotsset{compat=1.12}
```

输入示例代码：
```latex
\begin{tikzpicture}
\begin{axis}[
    axis lines = left,
    xlabel = $x$,
    ylabel = {$s(x)$},
]
%Below the red parabola is defined
\addplot [
    domain=-4:4,
    samples=100,
    color=black,
]
{1/(1+e^(-x))};

\addplot [
    domain=-4:4,
    samples=100,
    color=black,
]
{(1/(1+e^(-x)))*(1-(1/(1+e^(-x))))};

\end{axis}
\end{tikzpicture}
```

### 输入算法
`\usepackage[ruled]{algorithm2e}`
示例：
```latex
\begin{algorithm}
\caption{梯度下降\label{algo:gd}}
\tcc{批量梯度下降}
\Repeat{convergence}{
  $\theta_j \coloneq \theta_j + \alpha \sum_{i=1}^m
                    (y^{(i)} - h_\theta(x^{(i)}))x_j^{(i)}$ \quad (for every $j$)
}

\tcc{随机梯度下降}
\Repeat{}{
  \For{$i=1$ \KwTo $m$}{
    $\theta_j \coloneq \theta_j + \alpha
                    (y^{(i)} - h_\theta(x^{(i)}))x_j^{(i)}$ \quad (for every $j$)
  }
}
\end{algorithm}
```

### 引用代码
`\usepackage{minted}`
```latex
\begin{minted}[fontsize=\footnotesize]{python}
with tf.Session(graph=train_graph) as sess:
    sess.run(tf.global_variables_initializer())

    for epoch_i in range(epochs):
        for bchs_i, (x, y) in enumerate(train_batches):
            feed = {input_tensor: x, target_tensor: y}
            power_c, _ = sess.run([power_cost, optimizer], feed)
\end{minted}
```
编译时需要加上 `--shell-escape`。在sublime中编译时，需要改动`LaTeXTools.sublime`设置：
```json
	"builder_settings" : {
		"display_log" : false,
		"options": ["--shell-escape"],  //加这句！
  ...
```

### GBT-7714引用格式：
将`gbt7714-2005.bst`文件放到主文件的目录中。
Github: https://github.com/Haixing-Hu/GBT7714-2005-BibTeX-Style.git

