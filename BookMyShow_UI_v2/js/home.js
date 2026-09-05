const hm=document.getElementById("homeMovies");
if(hm)loadMovieGrid(hm,getMovies().then(a=>a.slice(0,8)));
const hc=document.getElementById("homeCities");
if(hc){getCities().then(list=>{hc.innerHTML=list.slice(0,10).map(c=>`<a class="city-card" href="pages/theaters.html?cityId=${c.id}"><b>📍 ${esc(c.name)}</b><span>${esc(c.state||"")}</span></a>`).join("")||'<div class="empty">No cities found.</div>'}).catch(()=>hc.innerHTML='<div class="empty">Cities could not be loaded.</div>')}
const input=document.getElementById("heroSearch"),suggest=document.getElementById("suggestions");
let timer;
if(input){input.addEventListener("input",()=>{clearTimeout(timer);const q=input.value.trim();if(!q){suggest.style.display="none";return}timer=setTimeout(async()=>{try{const a=(await searchMoviesByName(q)).slice(0,5);suggest.innerHTML=a.length?a.map(m=>`<div class="suggestion" data-id="${m.id}"><span>${esc(m.title)}</span><small>${esc(m.language||"")} • ${esc(m.genre||"")}</small></div>`).join(""):'<div class="suggestion">No movie found</div>';suggest.style.display="block";suggest.querySelectorAll("[data-id]").forEach(x=>x.onclick=()=>location.href=`pages/movie.html?id=${x.dataset.id}`)}catch{suggest.style.display="none"}},250)});
document.addEventListener("click",e=>{if(!e.target.closest(".search-box"))suggest.style.display="none"});
document.getElementById("heroSearchBtn").onclick=()=>{const q=input.value.trim();location.href=`pages/movies.html${q?"?search="+encodeURIComponent(q):""}`}
input.addEventListener("keydown",e=>{if(e.key==="Enter")document.getElementById("heroSearchBtn").click()})
}
