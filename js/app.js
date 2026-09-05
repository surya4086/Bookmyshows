function esc(v=""){return String(v).replace(/[&<>"']/g,m=>({"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#039;"}[m]))}
function toast(msg){const x=document.getElementById("toast");if(!x)return;const e=document.createElement("div");e.className="toast";e.textContent=msg;x.appendChild(e);setTimeout(()=>e.remove(),2800)}
function user(){try{return JSON.parse(localStorage.getItem("bm_user"))}catch{return null}}
function saveUser(u){localStorage.setItem("bm_user",JSON.stringify(u))}
function logout(){localStorage.removeItem("bm_user");location.href="../index.html"}
function root(){return location.pathname.includes("/pages/")?"../":"./"}
function renderNav(){
 const el=document.getElementById("navbar");if(!el)return;const r=root(),u=user(),page=document.body.dataset.page||"";
 el.innerHTML=`<nav class="navbar"><div class="nav-inner">
 <a class="logo" href="${r}index.html">Book<span>My</span>Show</a>
 <div class="nav-links"><a class="${page==="home"?"active":""}" href="${r}index.html">Home</a><a class="${page==="movies"?"active":""}" href="${r}pages/movies.html">Movies</a><a class="${page==="theaters"?"active":""}" href="${r}pages/theaters.html">Theatres</a><a class="${page==="bookings"?"active":""}" href="${r}pages/bookings.html">My Bookings</a><a class="${page==="admin"?"active":""}" href="${r}pages/admin.html">Admin</a></div>
 <div class="grow"></div><div class="nav-actions">${u?`<span class="hello">Hi, ${esc(u.name||"User")}</span><button class="btn outline" onclick="logout()">Logout</button>`:`<a class="btn outline" href="${r}pages/login.html">Login</a><a class="btn primary" href="${r}pages/signup.html">Sign Up</a>`}</div>
 </div></nav>`;
}
function movieCard(m){
 const r=root(),url=m.posterUrl||"";
 return `<article class="movie-card"><a href="${r}pages/movie.html?id=${m.id}">
 <div class="poster">${url?`<img src="${esc(url)}" alt="${esc(m.title)}" onerror="this.outerHTML='<div class=&quot;poster-fallback&quot;>🎬</div>'">`:`<div class="poster-fallback">🎬</div>`}<span class="rating">★ ${m.rating??"—"}</span></div>
 <div class="movie-info"><h3 class="movie-title">${esc(m.title)}</h3><div class="meta"><span>${esc(m.language||"")}</span><span>•</span><span>${esc(m.genre||"")}</span><span>•</span><span>${m.durationMinutes??"—"} min</span></div><div class="movie-cta"><span style="color:#929bb3;font-size:11px">From ₹${m.startingPrice??m.ticketPrice??"—"}</span><span class="btn primary">View shows</span></div></div></a></article>`;
}
async function loadMovieGrid(el,listPromise){
 el.innerHTML='<div class="loading">Loading movies...</div>';
 try{const list=await listPromise;el.innerHTML=list.length?list.map(movieCard).join(""):'<div class="empty">No movies found.</div>'}catch(e){console.error(e);el.innerHTML='<div class="empty">Could not connect to the movie API. Make sure Spring Boot is running on port 8080.</div>'}
}
renderNav();
