<%@page import= "java.util.ArrayList"%>
<%@page import="uidb.SearchResult"%>
<html>

<head>
    <link rel="stylesheet" href="style.css" />
</head>
<style>
    input[type="text"],
    button[type="submit"],
    button,
    textarea {
        border: none;
        outline: none;
    }
</style>

<body>
    <div class="SearchResults">
        <div class="page_direction_icons">
            <abbr title="Home"><a href="http://localhost:8080/Search_Engine/" id="searchPage_homeIcon"><img
                        src="home_icon.png" width="40" ; height="40" ; /></a></abbr>
            <form action="History" class="get2">
                <abbr title="History"><button type="submit" width="0" ; height="0" ; id="searchPage_historyIcon"> <img
                            src="history_icon.png" width="30" ; height="30" ; /></button></abbr>
            </form>

        </div>
        <div class="SearchContainer">
            <div class="searchbar1">
                <div class="icon">
                    <script src="https://cdn.lordicon.com/bhenfmcm.js"></script>
                    <lord-icon src="https://cdn.lordicon.com/zniqnylq.json" trigger="hover"
                        colors="primary:#4be1ec,secondary:#cb5eee" style="width:2rem;height:2rem">
                    </lord-icon>
                </div>
                <form action="search" class="get1">
                    <input style="font-size: 1.2rem;" type="text" id="sb" name="keyword" size="44" class="bar1"></input>
            </div>
            <div>
                <button style="width:8rem; height:2rem" type="submit" id="b3">Googie Search</button>
                </form>
            </div>
        </div>
        <div class="TableContainer">
            <table style="background-color:white;" border=2>
                <tr>
                    <th>Title</th>
                    <th>Link</th>
                </tr>
                <% ArrayList<SearchResult>results=(ArrayList<SearchResult>)request.getAttribute("results");
                    for(SearchResult result:results){
                %>
                    <tr>
                        <td>
                            <%out.println(result.getTitle());%>
                        </td>
                        <td><a href="<%out.println(result.getLink());%>">
                                <%out.println(result.getLink());%>
                            </a></td>
                    </tr>
                <% } %>
            </table>
        </div>
    </div>
</body>

</html>